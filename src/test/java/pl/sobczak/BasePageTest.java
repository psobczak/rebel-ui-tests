package pl.sobczak;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import pl.sobczak.config.listeners.DriverListener;
import pl.sobczak.config.listeners.TestListener;
import pl.sobczak.drivers.DriverFactory;
import pl.sobczak.drivers.DriverManager;
import pl.sobczak.drivers.DriverType;

@Listeners(TestListener.class)
public class BasePageTest {

    protected DriverManager driverManager;
    protected EventFiringWebDriver driver;

    @BeforeMethod
    public void setUp(ITestResult result) {
        driverManager = DriverFactory.createDriver(DriverType.CHROME);
        driver = driverManager.getDriver();
        DriverListener driverListener = new DriverListener();
        driver.register(driverListener);
        driver.manage().window().setSize(new Dimension(1920, 1080));
        result.setAttribute("driver", driver);
    }

    @AfterMethod
    public void tearDown() {
        driverManager.close();
    }
}
