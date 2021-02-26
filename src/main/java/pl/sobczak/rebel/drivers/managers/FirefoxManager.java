package pl.sobczak.rebel.drivers.managers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class FirefoxManager extends DriverManager {

    @Override
    public EventFiringWebDriver getDriver() {
        if (driver == null) {
            WebDriverManager.firefoxdriver().setup();
            driver = new EventFiringWebDriver(new FirefoxDriver(prepareBrowserOptions()));
        }
        return driver;
    }

    @Override
    protected FirefoxOptions prepareBrowserOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(true);
        options.addPreference("--start-debugger-server", 6000);
        options.addArguments("--start-debugger-server 6000");
        return options;
    }
}
