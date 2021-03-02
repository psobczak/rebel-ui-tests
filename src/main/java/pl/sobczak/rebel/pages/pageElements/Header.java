package pl.sobczak.rebel.pages.pageElements;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import pl.sobczak.rebel.pages.CategoryPage;

@Slf4j
public class Header {

    private final EventFiringWebDriver driver;
    @FindBy(css = "input.ui-autocomplete-input")
    private WebElement searchBar;
    @CacheLookup
    @FindBy(css = "div.menu div.container-fluid")
    private WebElement topMenu;
    @CacheLookup
    @FindBy(css = "li#toolbarAccount")
    private WebElement toolbarAccountButton;
    @FindBy(css = "a.btn.btn-block")
    private WebElement registerButton;


    public Header(EventFiringWebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Type into search bar '{query}'")
    public CategoryPage search(String query) {
        searchBar.clear();
        searchBar.sendKeys(query);
        searchBar.submit();
        return new CategoryPage(driver);
    }

    public WebElement getTopMenu() {
        return topMenu;
    }

    public WebElement getToolbarAccountButton() {
        return toolbarAccountButton;
    }

    public WebElement getRegisterButton() {
        return registerButton;
    }
}
