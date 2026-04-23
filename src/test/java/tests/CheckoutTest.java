package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.HomePage;
import pages.LoginPage;

public class CheckoutTest extends BaseTest {

    private void loginAndAddProduct() {
        new LoginPage().login("standard_user", "secret_sauce");
        HomePage homePage = new HomePage();
        homePage.addProductToCart("Sauce Labs Backpack");
        homePage.goToCart();
        new CartPage().proceedToCheckout();
    }

    @Test(description = "Checkout thành công với thông tin hợp lệ")
    public void testCheckoutSuccess() {
        loginAndAddProduct();

        CheckoutPage checkoutPage = new CheckoutPage();

        // Bước 1: Điền form thông tin khách hàng
        checkoutPage.fillAndContinue("Nguyen", "Van A", "700000");

        // Bước 2: Xem tổng kết & xác nhận
        Assert.assertNotNull(checkoutPage.getTotal(), "Phải hiển thị tổng tiền");
        checkoutPage.finishCheckout();

        // Bước 3: Kiểm tra đặt hàng thành công
        Assert.assertTrue(checkoutPage.isOrderSuccessful(), "Đơn hàng phải đặt thành công");
        Assert.assertEquals(checkoutPage.getSuccessMessage(), "Thank you for your order!");
    }

    @Test(description = "Checkout thất bại khi bỏ trống họ tên")
    public void testCheckoutMissingFirstName() {
        loginAndAddProduct();

        CheckoutPage checkoutPage = new CheckoutPage();
        checkoutPage.fillAndContinue("", "Van A", "700000");

        Assert.assertTrue(checkoutPage.isErrorDisplayed(), "Phải báo lỗi khi thiếu First Name");
        Assert.assertTrue(checkoutPage.getErrorMessage().contains("First Name"),
                "Thông báo phải nhắc nhập First Name");
    }

    @Test(description = "Checkout thất bại khi bỏ trống mã bưu chính")
    public void testCheckoutMissingPostalCode() {
        loginAndAddProduct();

        CheckoutPage checkoutPage = new CheckoutPage();
        checkoutPage.fillAndContinue("Nguyen", "Van A", "");

        Assert.assertTrue(checkoutPage.isErrorDisplayed(), "Phải báo lỗi khi thiếu Postal Code");
        Assert.assertTrue(checkoutPage.getErrorMessage().contains("Postal Code"),
                "Thông báo phải nhắc nhập Postal Code");
    }

    @Test(description = "Quay về trang chủ sau khi checkout thành công")
    public void testBackHomeAfterCheckout() {
        loginAndAddProduct();

        CheckoutPage checkoutPage = new CheckoutPage();
        checkoutPage.fillAndContinue("Nguyen", "Van A", "700000");
        checkoutPage.finishCheckout();
        checkoutPage.goBackHome();

        Assert.assertTrue(new HomePage().isOnHomePage(), "Phải quay được về trang chủ");
    }
}
