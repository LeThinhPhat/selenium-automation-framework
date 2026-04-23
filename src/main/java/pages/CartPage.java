package pages;

import base.BasePage;
import org.openqa.selenium.By;

public class CartPage extends BasePage {

    // --- Locators ---
    private final By cartTitle       = By.cssSelector(".title");
    private final By cartItems       = By.cssSelector(".cart_item");
    private final By checkoutButton  = By.id("checkout");
    private final By continueButton  = By.id("continue-shopping");

    private By removeButton(String productName) {
        String id = "remove-" + productName.toLowerCase().replace(" ", "-");
        return By.id(id);
    }

    // --- Actions ---
    public boolean isOnCartPage() {
        return getText(cartTitle).equals("Your Cart");
    }

    public int getItemCount() {
        return driver.findElements(cartItems).size();
    }

    public void removeProduct(String productName) {
        click(removeButton(productName));
    }

    public void proceedToCheckout() {
        click(checkoutButton);
    }

    public void continueShopping() {
        click(continueButton);
    }
}
