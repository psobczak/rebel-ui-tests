package pl.sobczak.drivers;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;


public abstract class DriverManager {

    protected EventFiringWebDriver driver;

    public abstract EventFiringWebDriver getDriver();

    protected abstract MutableCapabilities prepareBrowserOptions();

    public void close() {
        driver.close();
        driver.quit();
    }
}
