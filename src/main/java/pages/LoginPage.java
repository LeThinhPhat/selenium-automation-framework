package pages;

import base.BasePage;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    // --- Locators (saucedemo.com) ---
    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton   = By.id("login-button");
    private final By errorMessage  = By.cssSelector("[data-test='error']");

    // --- Actions ---
    public void enterUsername(String username) {
        fill(usernameField, username);
    }

    public void enterPassword(String password) {
        fill(passwordField, password);
    }

    public void clickLogin() {
        click(loginButton);
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    // --- Getters ---
    public String getErrorMessage() {
        return getText(errorMessage);
    }

    public boolean isErrorDisplayed() {
        return isDisplayed(errorMessage);
    }

    public boolean isOnLoginPage() {
        return isDisplayed(usernameField);
    }
}
