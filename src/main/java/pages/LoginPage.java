package pages;

import base.BasePage;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    // Popup đăng nhập
    private final By loginPopup    = By.cssSelector("#popupbox, .loginbox, .modal-login");
    private final By phoneInput    = By.cssSelector("input[name='sdt'], input[type='tel'], input[placeholder*='Số điện thoại'], input[placeholder*='điện thoại']");
    private final By passwordInput = By.cssSelector("input[type='password']");
    private final By submitBtn     = By.cssSelector(".btn-login, button.login, .loginbox button[type='submit']");
    private final By errorMsg      = By.cssSelector(".error-login, .msg-error, .txt-error, .login-error");
    private final By closePopup    = By.cssSelector("#popupbox .close, .modal-login .close, .loginbox .close");

    public boolean isPopupVisible() {
        return isDisplayed(loginPopup);
    }

    public void enterPhone(String phone) {
        fill(phoneInput, phone);
    }

    public void enterPassword(String password) {
        fill(passwordInput, password);
    }

    public void clickSubmit() {
        click(submitBtn);
    }

    public void login(String phone, String password) {
        enterPhone(phone);
        enterPassword(password);
        clickSubmit();
    }

    public boolean isErrorVisible() {
        return isDisplayed(errorMsg);
    }

    public String getErrorMessage() {
        return getText(errorMsg);
    }

    public void closePopup() {
        if (isDisplayed(closePopup)) {
            click(closePopup);
        }
    }
}
