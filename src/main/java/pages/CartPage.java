package pages;

import base.BasePage;
import org.openqa.selenium.By;

public class CartPage extends BasePage {

    private final By cartItems  = By.cssSelector(".product-list li, .lst-cart li, .item-cart");
    private final By emptyMsg   = By.cssSelector(".box-empty, .empty-cart, .cart-empty");
    private final By totalPrice = By.cssSelector(".total-price, .price-total, .txt-total");

    public boolean isOnCartPage() {
        return driver.getCurrentUrl().contains("/cart");
    }

    public int getItemCount() {
        return driver.findElements(cartItems).size();
    }

    public boolean isCartEmpty() {
        return isDisplayed(emptyMsg) || getItemCount() == 0;
    }

    public String getTotalPrice() {
        if (!isDisplayed(totalPrice)) return "0";
        return getText(totalPrice);
    }
}
