package pages;

import base.BasePage;
import org.openqa.selenium.By;

public class CheckoutPage extends BasePage {

    // --- Locators: Step 1 - Thông tin khách hàng ---
    private final By firstNameField  = By.id("first-name");
    private final By lastNameField   = By.id("last-name");
    private final By postalCodeField = By.id("postal-code");
    private final By continueButton  = By.id("continue");
    private final By errorMessage    = By.cssSelector("[data-test='error']");

    // --- Locators: Step 2 - Tổng kết đơn hàng ---
    private final By summaryTitle    = By.cssSelector(".title");
    private final By itemTotal       = By.cssSelector(".summary_subtotal_label");
    private final By taxLabel        = By.cssSelector(".summary_tax_label");
    private final By totalLabel      = By.cssSelector(".summary_total_label");
    private final By finishButton    = By.id("finish");

    // --- Locators: Step 3 - Hoàn thành ---
    private final By successHeader   = By.cssSelector(".complete-header");
    private final By backHomeButton  = By.id("back-to-products");

    // --- Actions: Điền form thông tin ---
    public void fillCustomerInfo(String firstName, String lastName, String postalCode) {
        fill(firstNameField, firstName);
        fill(lastNameField, lastName);
        fill(postalCodeField, postalCode);
    }

    public void clickContinue() {
        click(continueButton);
    }

    public void fillAndContinue(String firstName, String lastName, String postalCode) {
        fillCustomerInfo(firstName, lastName, postalCode);
        clickContinue();
    }

    // --- Actions: Xác nhận đơn hàng ---
    public String getItemTotal() {
        return getText(itemTotal);
    }

    public String getTax() {
        return getText(taxLabel);
    }

    public String getTotal() {
        return getText(totalLabel);
    }

    public void finishCheckout() {
        click(finishButton);
    }

    // --- Actions: Hoàn thành ---
    public String getSuccessMessage() {
        return getText(successHeader);
    }

    public boolean isOrderSuccessful() {
        return isDisplayed(successHeader);
    }

    public void goBackHome() {
        click(backHomeButton);
    }

    // --- Validation ---
    public boolean isErrorDisplayed() {
        return isDisplayed(errorMessage);
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }
}
