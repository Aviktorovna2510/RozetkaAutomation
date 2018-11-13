package utils.reporting;

import driver.DriverManager;
import io.qameta.allure.Attachment;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * This listener is used for Allure Reporting
 */
public class AllureListener implements ITestListener {

    private static final Logger logger = Logger.getLogger(AllureListener.class);

    @Override
    public void onTestStart(ITestResult Result) {
        logger.info(Result.getName() + " test case started");
    }

    @Override
    public void onTestSuccess(ITestResult Result) {
        logger.info(Result.getName() + " test case success");
    }

    @Override
    public void onTestFailure(ITestResult Result) {
        logger.error(Result.getName() + " TEST CASE FAILED!!!");
        saveScreenshot();
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    }

    @Override
    public void onStart(ITestContext iTestContext) {
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    private byte[] saveScreenshot() {
        return ((TakesScreenshot) DriverManager.getCurrentDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
