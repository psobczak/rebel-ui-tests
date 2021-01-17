package pl.sobczak.config.listeners;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import pl.sobczak.utils.Screenshot;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        EventFiringWebDriver driver = (EventFiringWebDriver) result.getAttribute("driver");
        System.out.println(result.getName() + " just started!");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        EventFiringWebDriver driver = (EventFiringWebDriver) result.getAttribute("driver");
        System.out.println(result.getName() + " finished successfully!");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        EventFiringWebDriver driver = (EventFiringWebDriver) result.getAttribute("driver");
        System.out.println(result.getName() + " failed miserably!");
        Screenshot.takeScreenshot(driver, result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }
}
