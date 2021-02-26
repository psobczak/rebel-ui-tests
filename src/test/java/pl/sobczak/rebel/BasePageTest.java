package pl.sobczak.rebel;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import pl.sobczak.rebel.config.listeners.DriverListener;
import pl.sobczak.rebel.config.listeners.TestListener;
import pl.sobczak.rebel.drivers.DriverManagerFactory;
import pl.sobczak.rebel.drivers.managers.DriverManager;

@Listeners(TestListener.class)
public class BasePageTest {

    protected DriverManager driverManager;
    protected EventFiringWebDriver driver;

    @BeforeMethod
    public void setUp(ITestResult result) {
        driverManager = DriverManagerFactory.createManager();
        driver = driverManager.getDriver();
        DriverListener driverListener = new DriverListener();
        driver.register(driverListener);
        driver.manage().window().setSize(new Dimension(1920, 1080));
        result.setAttribute("driver", driver);
    }

    @AfterMethod
    public void tearDown() {
        driverManager.quit();
    }
}
