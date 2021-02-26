package pl.sobczak.rebel.config.listeners;

import io.qameta.allure.Allure;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;


@Slf4j
public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        EventFiringWebDriver driver = (EventFiringWebDriver) result.getAttribute("driver");
        log.info(result.getName() + " just started!");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        EventFiringWebDriver driver = (EventFiringWebDriver) result.getAttribute("driver");
        log.info(result.getName() + " finished successfully!");

    }

    @Override
    public void onTestFailure(ITestResult result) {
        EventFiringWebDriver driver = (EventFiringWebDriver) result.getAttribute("driver");
        Allure.addAttachment(result.getName(), new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        log.info(result.getName() + " failed!");
//        Screenshot.takeScreenshot(driver, result.getName());
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
        log.info("Run total {} tests", context.getFailedTests().size());
    }
}
