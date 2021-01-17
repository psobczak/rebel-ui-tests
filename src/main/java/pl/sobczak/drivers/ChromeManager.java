package pl.sobczak.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class ChromeManager extends DriverManager {

    @Override
    public EventFiringWebDriver getDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new EventFiringWebDriver(new ChromeDriver(prepareBrowserOptions()));
        }
        return driver;
    }

    @Override
    protected MutableCapabilities prepareBrowserOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions
                .addArguments("--headless")
                .addArguments("--remote-debugging-port=9222");
//                .addArguments("--lang=pl")
//                .addArguments("--disable-popup-blocking");
        return chromeOptions;
    }
}
