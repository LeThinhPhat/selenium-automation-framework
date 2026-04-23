package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPage extends BasePage {

    // --- Locators ---
    private final By searchInput   = By.id("skw");
    private final By searchButton  = By.cssSelector("form.header__search button[type='submit']");
    private final By searchResults = By.cssSelector("li.product_suggest");
    private final By productName   = By.cssSelector("li.product_suggest h3");
    // Giá có thể là strong.price hoặc span.price
    private final By productPrice  = By.cssSelector("li.product_suggest strong.price, li.product_suggest span.price, li.product_suggest .price");

    // Popup banner che màn hình
    private final By popupBanner   = By.cssSelector(".popup-banner");
    private final By popupClose    = By.cssSelector(".popup-banner .close, .popup-banner [class*='close'], .popup-banner button");

    // Kết quả trang đầy đủ (sau khi nhấn Enter)
    private final By resultItems   = By.cssSelector(".item[data-id]");

    // --- Đóng popup nếu có ---
    public void closePopupIfPresent() {
        try {
            if (isDisplayed(popupBanner)) {
                // Thử click nút đóng
                if (isDisplayed(popupClose)) {
                    click(popupClose);
                } else {
                    // Ẩn popup bằng JavaScript
                    WebElement popup = driver.findElement(popupBanner);
                    ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].style.display='none';", popup);
                }
                Thread.sleep(500);
            }
        } catch (Exception ignored) {}
    }

    // --- Actions ---
    public void searchFor(String keyword) {
        closePopupIfPresent();
        fill(searchInput, keyword);
        try { Thread.sleep(1500); } catch (InterruptedException ignored) {}
    }

    public void submitSearch(String keyword) {
        closePopupIfPresent();
        fill(searchInput, keyword);
        // Dùng JavaScript click để tránh bị che bởi popup
        WebElement btn = driver.findElement(searchButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
    }

    // --- Getters ---
    public List<WebElement> getSuggestResults() {
        wait.until(d -> !d.findElements(searchResults).isEmpty());
        return driver.findElements(searchResults);
    }

    public String getFirstSuggestName() {
        return driver.findElement(productName).getText();
    }

    public String getFirstSuggestPrice() {
        List<WebElement> prices = driver.findElements(productPrice);
        if (!prices.isEmpty()) return prices.get(0).getText();
        return "Không tìm thấy giá";
    }

    public int getSuggestCount() {
        return driver.findElements(searchResults).size();
    }

    public List<WebElement> getResultItems() {
        wait.until(d -> !d.findElements(resultItems).isEmpty());
        return driver.findElements(resultItems);
    }

    public boolean hasResults() {
        return !driver.findElements(resultItems).isEmpty();
    }

    public boolean isSuggestDisplayed() {
        return isDisplayed(searchResults);
    }
}
