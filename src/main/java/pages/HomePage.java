package pages;

import base.BasePage;
import org.openqa.selenium.By;

public class HomePage extends BasePage {

    // --- Locators ---
    private final By pageTitle      = By.cssSelector(".title");
    private final By productList    = By.cssSelector(".inventory_list");
    private final By sortDropdown   = By.cssSelector("[data-test='product-sort-container']");
    private final By cartIcon       = By.cssSelector(".shopping_cart_link");
    private final By cartBadge      = By.cssSelector(".shopping_cart_badge");
    private final By menuButton     = By.id("react-burger-menu-btn");
    private final By logoutLink     = By.id("logout_sidebar_link");

    // Lấy nút "Add to cart" theo tên sản phẩm
    private By addToCartButton(String productName) {
        String id = "add-to-cart-" + productName.toLowerCase().replace(" ", "-");
        return By.id(id);
    }

    private By productLink(String productName) {
        return By.xpath("//div[@class='inventory_item_name' and text()='" + productName + "']");
    }

    // --- Actions ---
    public boolean isOnHomePage() {
        return isDisplayed(productList);
    }

    public String getPageTitle() {
        return getText(pageTitle);
    }

    public void addProductToCart(String productName) {
        scrollTo(addToCartButton(productName));
        click(addToCartButton(productName));
    }

    public void openProduct(String productName) {
        click(productLink(productName));
    }

    public void sortBy(String option) {
        // option: "az", "za", "lohi", "hilo"
        new org.openqa.selenium.support.ui.Select(
                driver.findElement(sortDropdown)
        ).selectByValue(option);
    }

    public void goToCart() {
        click(cartIcon);
    }

    public int getCartCount() {
        if (!isDisplayed(cartBadge)) return 0;
        return Integer.parseInt(getText(cartBadge));
    }

    public void logout() {
        click(menuButton);
        click(logoutLink);
    }
}
