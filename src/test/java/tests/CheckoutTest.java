package tests;

import base.BaseTest;
import listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import utils.DriverManager;

@Listeners(TestListener.class)
public class CheckoutTest extends BaseTest {

    @Test(description = "Trang giỏ hàng load thành công")
    public void testCartPageLoads() {
        DriverManager.getDriver().get(BASE_URL + "/gio-hang");
        CartPage cartPage = new CartPage();
        Assert.assertTrue(cartPage.isOnCartPage(), "Phải vào được trang giỏ hàng (/cart)");
        TestListener.getTest().info("URL: " + DriverManager.getDriver().getCurrentUrl());
    }

    @Test(description = "Giỏ hàng trống khi chưa thêm sản phẩm")
    public void testCartIsEmpty() {
        DriverManager.getDriver().get(BASE_URL + "/gio-hang");
        CartPage cartPage = new CartPage();
        Assert.assertTrue(cartPage.isOnCartPage(), "Phải vào được trang giỏ hàng");
        Assert.assertTrue(cartPage.isCartEmpty(), "Giỏ hàng phải trống khi chưa thêm sản phẩm");
    }

    @Test(description = "Click icon giỏ hàng chuyển trang thành công")
    public void testCartIconNavigation() {
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.isCartVisible(), "Icon giỏ hàng phải hiển thị");
        homePage.goToCart();

        CartPage cartPage = new CartPage();
        Assert.assertTrue(cartPage.isOnCartPage(), "Click giỏ hàng phải chuyển đến /cart");
        TestListener.getTest().info("URL: " + DriverManager.getDriver().getCurrentUrl());
    }

    @Test(description = "URL trang giỏ hàng chứa /cart")
    public void testCartPageUrl() {
        DriverManager.getDriver().get(BASE_URL + "/gio-hang");
        String url = DriverManager.getDriver().getCurrentUrl();
        Assert.assertTrue(url.contains("/cart"), "URL phải chứa '/cart', thực tế: " + url);
    }
}
