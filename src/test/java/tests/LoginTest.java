package tests;

import base.BaseTest;
import listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utils.DriverManager;

public class LoginTest extends BaseTest {

    @Test(description = "Trang chủ load thành công - có thanh tìm kiếm", priority = 1)
    public void testHomePageLoads() {
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.isLoaded(), "Trang chủ phải hiển thị thanh tìm kiếm");
        TestListener.getTest().info("URL: " + DriverManager.getDriver().getCurrentUrl());
    }

    @Test(description = "Icon giỏ hàng hiển thị trên header", priority = 2)
    public void testCartIconVisible() {
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.isCartVisible(), "Icon giỏ hàng phải hiển thị trên header");
    }

    @Test(description = "Tiêu đề trang chủ chứa tên thương hiệu", priority = 3)
    public void testHomePageTitle() {
        String title = DriverManager.getDriver().getTitle();
        Assert.assertFalse(title.isEmpty(), "Tiêu đề trang không được rỗng");
        Assert.assertTrue(
            title.toLowerCase().contains("thế giới di động") || title.toLowerCase().contains("thegioididong"),
            "Tiêu đề phải chứa tên thương hiệu, nhưng nhận được: " + title
        );
        TestListener.getTest().info("Tiêu đề: " + title);
    }

    @Test(description = "Trang chủ load đúng domain", priority = 4)
    public void testHomePageDomain() {
        String url = DriverManager.getDriver().getCurrentUrl();
        Assert.assertTrue(
            url.startsWith(BASE_URL),
            "URL phải bắt đầu bằng " + BASE_URL + " nhưng nhận được: " + url
        );
    }

    @Test(description = "Click login mở popup đăng nhập", priority = 5)
    public void testLoginPopupOpens() {
        HomePage homePage = new HomePage();
        homePage.clickLogin();
        LoginPage loginPage = new LoginPage();
        Assert.assertTrue(loginPage.isPopupVisible(), "Popup đăng nhập phải hiển thị sau khi click");
        TestListener.getTest().info("Popup đăng nhập đã xuất hiện");
    }

    @Test(description = "Đăng nhập sai mật khẩu hiển thị thông báo lỗi", priority = 6)
    public void testLoginWithInvalidCredentials() {
        HomePage homePage = new HomePage();
        homePage.clickLogin();

        LoginPage loginPage = new LoginPage();
        Assert.assertTrue(loginPage.isPopupVisible(), "Popup đăng nhập phải mở trước khi nhập thông tin");

        loginPage.login("0900000000", "wrongpassword123");

        Assert.assertTrue(
            loginPage.isErrorVisible(),
            "Phải hiển thị thông báo lỗi khi đăng nhập sai"
        );
        TestListener.getTest().info("Lỗi hiển thị: " + loginPage.getErrorMessage());
    }

    @Test(description = "Đóng popup đăng nhập thành công", priority = 7)
    public void testCloseLoginPopup() {
        HomePage homePage = new HomePage();
        homePage.clickLogin();

        LoginPage loginPage = new LoginPage();
        Assert.assertTrue(loginPage.isPopupVisible(), "Popup phải mở trước khi đóng");

        loginPage.closePopup();
        Assert.assertFalse(loginPage.isPopupVisible(), "Popup phải đóng sau khi click nút đóng");
    }
}
