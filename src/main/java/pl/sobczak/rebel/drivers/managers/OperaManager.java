package pl.sobczak.rebel.drivers.managers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class OperaManager extends DriverManager {

    @Override
    public EventFiringWebDriver getDriver() {
        if (driver == null) {
            WebDriverManager.operadriver().setup();
            driver = new EventFiringWebDriver(new OperaDriver());
        }

        return driver;
    }

    @Override
    protected MutableCapabilities prepareBrowserOptions() {
        return null;
    }
}
