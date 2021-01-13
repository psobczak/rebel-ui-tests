package pl.sobczak.pages;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import pl.sobczak.drivers.DriverFactory;
import pl.sobczak.drivers.DriverManager;
import pl.sobczak.drivers.DriverType;

public class BasePageTest {

    protected DriverManager driverManager;
    protected EventFiringWebDriver driver;

    @BeforeEach
    public void setUp() {
        driverManager = DriverFactory.createDriver(DriverType.CHROME);
        driver = driverManager.getDriver();
    }

    @AfterEach
    public void tearDown() {
        driverManager.close();
    }
}
