package listeners;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ExtentReportManager;

public class TestListener implements ITestListener {

    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public static ExtentTest getTest() {
        return extentTest.get();
    }

    @Override
    public void onTestStart(ITestResult result) {
        String description = result.getMethod().getDescription();
        String name = description != null && !description.isEmpty()
                ? description
                : result.getMethod().getMethodName();

        ExtentTest test = ExtentReportManager.getInstance().createTest(name);
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, "PASS");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.get().log(Status.FAIL, result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.get().log(Status.SKIP, "Test bị bỏ qua");
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentReportManager.getInstance().flush();
    }
}
