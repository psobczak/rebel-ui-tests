package pl.sobczak.rebel.drivers.managers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class EdgeManager extends DriverManager {

    @Override
    public EventFiringWebDriver getDriver() {
        if (driver == null) {
            WebDriverManager.edgedriver().setup();
            driver = new EventFiringWebDriver(new EdgeDriver());
        }

        return driver;
    }

    @Override
    protected MutableCapabilities prepareBrowserOptions() {
        return null;
    }
}
