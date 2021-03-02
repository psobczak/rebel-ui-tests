package pl.sobczak.rebel.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import pl.sobczak.rebel.pages.pageElements.Header;
import pl.sobczak.rebel.utils.WaitHelper;

public class BasePage {

    protected EventFiringWebDriver driver;
    protected WaitHelper waitHelper;
    private final JavascriptExecutor executor;

    private final Header header;

    public BasePage(EventFiringWebDriver driver) {
        this.driver = driver;
        waitHelper = new WaitHelper(driver);
        executor = driver;
        header = new Header(driver);
        PageFactory.initElements(driver, this);
    }

    protected String getTextWithCss(WebElement element, String cssSelector) {
        return element.findElement(By.cssSelector(cssSelector)).getText().trim();
    }

    protected void executeJsScript(String script) {
        executor.executeScript(script);
    }

    protected void highlightElement(WebElement element) {
        executor.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;')", element);
    }

    protected void waitAndClick(WebElement element) {
        waitHelper.waitForElementToBeClickable(element);
        element.click();
    }

    protected void clearAndType(WebElement element, String text) {
        waitHelper.waitForVisibilityOfElement(element);
        waitHelper.waitForElementToBeClickable(element);
        element.clear();
        element.sendKeys(text);
    }

    public Header getHeader() {
        return header;
    }
}
