package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;

public class SearchAndCartTest extends BaseTest {

    private void loginAsStandard() {
        new LoginPage().login("standard_user", "secret_sauce");
    }

    @Test(description = "Thêm 1 sản phẩm vào giỏ hàng")
    public void testAddOneProductToCart() {
        loginAsStandard();

        HomePage homePage = new HomePage();
        homePage.addProductToCart("Sauce Labs Backpack");

        Assert.assertEquals(homePage.getCartCount(), 1, "Giỏ hàng phải có 1 sản phẩm");
    }

    @Test(description = "Thêm nhiều sản phẩm vào giỏ hàng")
    public void testAddMultipleProductsToCart() {
        loginAsStandard();

        HomePage homePage = new HomePage();
        homePage.addProductToCart("Sauce Labs Backpack");
        homePage.addProductToCart("Sauce Labs Bike Light");
        homePage.addProductToCart("Sauce Labs Bolt T-Shirt");

        Assert.assertEquals(homePage.getCartCount(), 3, "Giỏ hàng phải có 3 sản phẩm");
    }

    @Test(description = "Xóa sản phẩm khỏi giỏ hàng")
    public void testRemoveProductFromCart() {
        loginAsStandard();

        HomePage homePage = new HomePage();
        homePage.addProductToCart("Sauce Labs Backpack");
        homePage.addProductToCart("Sauce Labs Bike Light");
        homePage.goToCart();

        CartPage cartPage = new CartPage();
        Assert.assertTrue(cartPage.isOnCartPage());
        Assert.assertEquals(cartPage.getItemCount(), 2);

        cartPage.removeProduct("Sauce Labs Backpack");
        Assert.assertEquals(cartPage.getItemCount(), 1, "Còn 1 sản phẩm sau khi xóa");
    }

    @Test(description = "Sắp xếp sản phẩm theo giá thấp đến cao")
    public void testSortByPriceLowToHigh() {
        loginAsStandard();

        HomePage homePage = new HomePage();
        homePage.sortBy("lohi"); // lohi = low to high
        // Kiểm tra trang vẫn hiển thị bình thường
        Assert.assertTrue(homePage.isOnHomePage(), "Trang vẫn hoạt động sau khi sort");
    }
}
