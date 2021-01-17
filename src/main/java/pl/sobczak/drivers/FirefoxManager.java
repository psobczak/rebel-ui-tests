package pl.sobczak.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class FirefoxManager extends DriverManager {

    @Override
    public EventFiringWebDriver getDriver() {
        if (driver == null) {
            WebDriverManager.firefoxdriver().setup();
            driver = new EventFiringWebDriver(new FirefoxDriver());
        }
        return driver;
    }

    @Override
    protected MutableCapabilities prepareBrowserOptions() {
        return null;
    }
}
