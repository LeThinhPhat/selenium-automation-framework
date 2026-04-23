package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test(description = "Đăng nhập thành công với tài khoản hợp lệ")
    public void testLoginSuccess() {
        LoginPage loginPage = new LoginPage();
        loginPage.login("standard_user", "secret_sauce");

        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.isOnHomePage(), "Phải vào được trang chủ sau khi login");
    }

    @Test(description = "Đăng nhập thất bại với sai mật khẩu")
    public void testLoginWrongPassword() {
        LoginPage loginPage = new LoginPage();
        loginPage.login("standard_user", "wrong_password");

        Assert.assertTrue(loginPage.isErrorDisplayed(), "Phải hiện thông báo lỗi");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username and password do not match"),
                "Nội dung lỗi không đúng");
    }

    @Test(description = "Đăng nhập thất bại với tài khoản bị khóa")
    public void testLoginLockedUser() {
        LoginPage loginPage = new LoginPage();
        loginPage.login("locked_out_user", "secret_sauce");

        Assert.assertTrue(loginPage.isErrorDisplayed(), "Phải hiện thông báo lỗi");
        Assert.assertTrue(loginPage.getErrorMessage().contains("locked out"),
                "Phải báo tài khoản bị khóa");
    }

    @Test(description = "Đăng xuất thành công")
    public void testLogout() {
        LoginPage loginPage = new LoginPage();
        loginPage.login("standard_user", "secret_sauce");

        HomePage homePage = new HomePage();
        homePage.logout();

        // Sau logout phải quay về trang login (có ô input username)
        Assert.assertTrue(new LoginPage().isOnLoginPage(), "Phải quay về trang login");
    }
}
