package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SearchPage;

public class SearchTest extends BaseTest {

    @Test(description = "Tìm kiếm hiện gợi ý sản phẩm")
    public void testSearchSuggest() {
        SearchPage searchPage = new SearchPage();
        searchPage.searchFor("iPhone");

        Assert.assertTrue(searchPage.isSuggestDisplayed(), "Phải hiện danh sách gợi ý");
        Assert.assertTrue(searchPage.getSuggestCount() > 0, "Phải có ít nhất 1 gợi ý");

        System.out.println("Gợi ý đầu tiên: " + searchPage.getFirstSuggestName());
        System.out.println("Giá: " + searchPage.getFirstSuggestPrice());
    }

    @Test(description = "Tìm kiếm và xem kết quả đầy đủ")
    public void testSearchFullResult() {
        SearchPage searchPage = new SearchPage();
        searchPage.submitSearch("Samsung");

        Assert.assertTrue(searchPage.hasResults(), "Phải có kết quả tìm kiếm");
        System.out.println("Số sản phẩm tìm thấy: " + searchPage.getResultItems().size());
    }

    @Test(description = "Tìm kiếm từ khóa không tồn tại")
    public void testSearchNoResult() {
        SearchPage searchPage = new SearchPage();
        searchPage.submitSearch("xyzxyzxyz12345");

        Assert.assertFalse(searchPage.hasResults(), "Không có kết quả với từ khóa rác");
    }
}
