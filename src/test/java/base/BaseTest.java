package base;

import listeners.TestListener;
import org.testng.annotations.*;
import utils.DriverManager;

@Listeners(TestListener.class)
public class BaseTest {

    protected static final String BASE_URL = "https://www.thegioididong.com";

    @BeforeMethod
    public void setUp() {
        DriverManager.initDriver(false);
        DriverManager.getDriver().get(BASE_URL);
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
