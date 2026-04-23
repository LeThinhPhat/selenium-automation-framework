package tests;

import base.BaseTest;
import listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.SearchPage;

@Listeners(TestListener.class)
public class SearchTest extends BaseTest {

    @Test(description = "Tìm kiếm hiện gợi ý sản phẩm")
    public void testSearchSuggest() {
        SearchPage searchPage = new SearchPage();
        searchPage.searchFor("iPhone");

        Assert.assertTrue(searchPage.isSuggestDisplayed(), "Phải hiện danh sách gợi ý");
        Assert.assertTrue(searchPage.getSuggestCount() > 0, "Phải có ít nhất 1 gợi ý");

        TestListener.getTest().info("Gợi ý đầu tiên: " + searchPage.getFirstSuggestName());
        TestListener.getTest().info("Giá: " + searchPage.getFirstSuggestPrice());
    }

    @Test(description = "Tìm kiếm và xem kết quả đầy đủ")
    public void testSearchFullResult() {
        SearchPage searchPage = new SearchPage();
        searchPage.submitSearch("Samsung");

        Assert.assertTrue(searchPage.hasResults(), "Phải có kết quả tìm kiếm");
        int count = searchPage.getResultItems().size();
        TestListener.getTest().info("Số sản phẩm tìm thấy: " + count);
    }

    @Test(description = "Tìm kiếm từ khóa không tồn tại")
    public void testSearchNoResult() {
        SearchPage searchPage = new SearchPage();
        searchPage.submitSearch("xyzxyzxyz12345");

        Assert.assertFalse(searchPage.hasResults(), "Không có kết quả với từ khóa rác");
    }
}
