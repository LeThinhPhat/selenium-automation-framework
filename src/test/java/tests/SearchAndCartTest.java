package tests;

import base.BaseTest;
import listeners.TestListener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.ProductPage;
import pages.SearchPage;
import utils.DriverManager;

import java.util.List;

@Listeners(TestListener.class)
public class SearchAndCartTest extends BaseTest {

    @Test(description = "Tìm kiếm iPhone và click vào sản phẩm đầu tiên")
    public void testSearchAndClickProduct() {
        SearchPage searchPage = new SearchPage();
        searchPage.submitSearch("iPhone");

        Assert.assertTrue(searchPage.hasResults(), "Phải có kết quả tìm kiếm iPhone");

        List<WebElement> items = searchPage.getResultItems();
        Assert.assertFalse(items.isEmpty(), "Danh sách sản phẩm không được rỗng");

        items.get(0).click();

        ProductPage productPage = new ProductPage();
        Assert.assertTrue(productPage.isOnProductPage(), "Phải vào trang chi tiết sản phẩm");
        TestListener.getTest().info("Sản phẩm: " + productPage.getProductName());
    }

    @Test(description = "Sản phẩm trong kết quả tìm kiếm có hiển thị giá")
    public void testSearchResultHasPrice() {
        SearchPage searchPage = new SearchPage();
        searchPage.submitSearch("Samsung");

        Assert.assertTrue(searchPage.hasResults(), "Phải có kết quả");

        List<WebElement> prices = DriverManager.getDriver()
                .findElements(By.cssSelector(".item[data-id] .price, .item[data-id] .box-p strong"));
        Assert.assertFalse(prices.isEmpty(), "Sản phẩm trong kết quả phải có giá");
        TestListener.getTest().info("Giá sản phẩm đầu tiên: " + prices.get(0).getText());
    }

    @Test(description = "Trang chi tiết sản phẩm có nút thêm vào giỏ")
    public void testProductPageHasAddToCart() {
        SearchPage searchPage = new SearchPage();
        searchPage.submitSearch("iPhone");

        Assert.assertTrue(searchPage.hasResults());
        searchPage.getResultItems().get(0).click();

        ProductPage productPage = new ProductPage();
        Assert.assertTrue(productPage.isOnProductPage(), "Phải vào trang sản phẩm");
        Assert.assertTrue(productPage.hasAddToCartButton(), "Phải có nút thêm vào giỏ hàng");
    }

    @Test(description = "Trang sản phẩm hiển thị giá bán")
    public void testProductPageHasPrice() {
        SearchPage searchPage = new SearchPage();
        searchPage.submitSearch("Laptop");

        Assert.assertTrue(searchPage.hasResults());
        searchPage.getResultItems().get(0).click();

        ProductPage productPage = new ProductPage();
        Assert.assertTrue(productPage.hasPrice(), "Trang sản phẩm phải hiển thị giá");
        TestListener.getTest().info("Giá: " + productPage.getProductPrice());
    }
}
