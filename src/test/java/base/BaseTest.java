package base;

import org.testng.annotations.*;
import utils.DriverManager;

public class BaseTest {

    // Đổi URL này thành website e-commerce bạn muốn test
    protected static final String BASE_URL = "https://www.thegioididong.com";

    @BeforeMethod
    public void setUp() {
        DriverManager.initDriver(false); // false = mở Chrome có giao diện
        DriverManager.getDriver().get(BASE_URL);
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
