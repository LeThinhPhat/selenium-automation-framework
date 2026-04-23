package tests;

import base.BaseTest;
import listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.DriverManager;

@Listeners(TestListener.class)
public class LoginTest extends BaseTest {

    @Test(description = "Trang chủ load thành công - có thanh tìm kiếm")
    public void testHomePageLoads() {
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.isLoaded(), "Trang chủ phải hiển thị thanh tìm kiếm");
        TestListener.getTest().info("URL: " + DriverManager.getDriver().getCurrentUrl());
    }

    @Test(description = "Icon giỏ hàng hiển thị trên header")
    public void testCartIconVisible() {
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.isCartVisible(), "Icon giỏ hàng phải hiển thị trên header");
    }

    @Test(description = "Tiêu đề trang chủ đúng")
    public void testHomePageTitle() {
        String title = DriverManager.getDriver().getTitle();
        Assert.assertFalse(title.isEmpty(), "Tiêu đề trang không được rỗng");
        TestListener.getTest().info("Tiêu đề: " + title);
    }

    @Test(description = "Trang chủ load đúng domain")
    public void testHomePageDomain() {
        String url = DriverManager.getDriver().getCurrentUrl();
        Assert.assertTrue(url.contains("thegioididong.com"), "Domain phải là thegioididong.com");
    }
}
