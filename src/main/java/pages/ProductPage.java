package pages;

import base.BasePage;
import org.openqa.selenium.By;

public class ProductPage extends BasePage {

    private final By productName  = By.cssSelector("h1");
    // b.from-price cho iPhone 15-, .box04 cho iPhone 16+ và Laptop
    private final By productPrice = By.cssSelector("b.from-price, .box04");
    // a.btn-buynow: "Thêm vào giỏ" (white) và "Mua ngay" (buy)
    private final By addToCartBtn = By.cssSelector("a.btn-buynow");

    public boolean isOnProductPage() {
        String url = driver.getCurrentUrl();
        return isDisplayed(productName) && !url.equals("https://www.thegioididong.com/");
    }

    public String getProductName() {
        return getText(productName);
    }

    public String getProductPrice() {
        if (!isDisplayed(productPrice)) return "";
        return getText(productPrice);
    }

    public boolean hasPrice() {
        return isDisplayed(productPrice);
    }

    public boolean hasAddToCartButton() {
        return isDisplayed(addToCartBtn);
    }
}
