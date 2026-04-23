package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {

    private final By searchInput = By.id("skw");
    private final By cartLink    = By.cssSelector("a.header__cart");
    private final By cartCount   = By.cssSelector("a.header__cart .cart-number, a.header__cart span.num");
    private final By loginLink   = By.cssSelector("a.name-order");

    public boolean isLoaded() {
        return isDisplayed(searchInput);
    }

    public boolean isCartVisible() {
        return isDisplayed(cartLink);
    }

    public void clickLogin() {
        WebElement el = driver.findElement(loginLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
    }

    public void goToCart() {
        WebElement el = driver.findElement(cartLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
    }

    public String getCartCount() {
        if (!isDisplayed(cartCount)) return "0";
        return getText(cartCount);
    }
}
