package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("target/reports/extent-report.html");
            spark.config().setDocumentTitle("TGDD Automation Report");
            spark.config().setReportName("Thegioididong.com - Test Report");
            spark.config().setTheme(Theme.STANDARD);
            spark.config().setEncoding("UTF-8");

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Website", "https://www.thegioididong.com");
            extent.setSystemInfo("Browser", "Chrome");
            extent.setSystemInfo("Framework", "Selenium 4 + TestNG");
        }
        return extent;
    }
}
