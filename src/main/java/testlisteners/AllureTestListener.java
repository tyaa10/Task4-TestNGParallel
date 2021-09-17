package testlisteners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AllureTestListener implements ITestListener {

    private static final Logger LOGGER = LogManager.getLogger(AllureTestListener.class);

    @Override
    public void onTestStart(ITestResult iTestResult) {
        LOGGER.info(iTestResult.getMethod().getMethodName() + " Started");
        LOGGER.info(iTestResult.getMethod().getDescription());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        LOGGER.info(String.format("Test %s passed", iTestResult.getTestName()));
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        LOGGER.info(String.format("Test %s failed", iTestResult.getTestName()));
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        LOGGER.info(String.format("Test %s skipped", iTestResult.getTestName()));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        LOGGER.info("Test failed but it is in defined success ratio "+ iTestResult.getThrowable());
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        LOGGER.info("=========== " + iTestContext.getName() + " Started ===============");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        LOGGER.info("=========== " + iTestContext.getName() + " Finished ===============");
    }
}
