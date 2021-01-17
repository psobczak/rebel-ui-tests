package pl.sobczak.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import pl.sobczak.utils.WaitHelper;

public class BasePage {

    protected EventFiringWebDriver driver;
    protected WaitHelper waitHelper;

    public BasePage(EventFiringWebDriver driver) {
        this.driver = driver;
        waitHelper = new WaitHelper(driver);
        PageFactory.initElements(driver, this);
    }

    protected String getTextWithCss(WebElement element, String cssSelector) {
        return element.findElement(By.cssSelector(cssSelector)).getText().trim();
    }
}
