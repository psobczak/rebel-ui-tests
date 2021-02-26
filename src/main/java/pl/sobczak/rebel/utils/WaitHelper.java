package pl.sobczak.rebel.utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class WaitHelper {

    private final long WAIT_TIME = 10L;
    private final EventFiringWebDriver driver;
    private final WebDriverWait wait;

    public WaitHelper(EventFiringWebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, WAIT_TIME);
    }

    public void waitForVisibilityOfElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForVisibilityOfElements(List<WebElement> elements) {
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
