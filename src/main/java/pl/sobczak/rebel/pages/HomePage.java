package pl.sobczak.rebel.pages;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import pl.sobczak.rebel.models.Bestseller;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class HomePage extends BasePage {

    @FindBy(css = "input.ui-autocomplete-input")
    private WebElement searchBar;

    @FindBy(css = "div.products--small div.product__wrapper")
    private List<WebElement> bestsellers;

    @CacheLookup
    @FindBy(css = "div.menu div.container-fluid")
    private WebElement topMenu;

    @CacheLookup
    @FindBy(css = "li#toolbarAccount")
    private WebElement toolbarAccountButton;

    @FindBy(css = "a.btn.btn-block")
    private WebElement registerButton;

    public HomePage(EventFiringWebDriver driver) {
        super(driver);
        driver.get("https://www.rebel.pl/");
    }

    @Step("Type '{text}' into top search bar")
    public HomePage typeIntoSearchBar(String text) {
        searchBar.clear();
        searchBar.sendKeys(text);
        return this;
    }

    @Step("Click toolbar account button")
    public HomePage clickToolbarAccountButton() {
        waitAndClick(toolbarAccountButton);
        return this;
    }

    @Step("Click register button")
    public RegisterPage clickRegisterButton() {
        Actions actions = new Actions(driver);
        actions.moveToElement(registerButton)
                .click()
                .build()
                .perform();
        return new RegisterPage(driver);
    }

    @Step("Get all displayed bestsellers")
    public List<Bestseller> getBestsellers() {
        List<Bestseller> results = new ArrayList<>();
        for (WebElement element : bestsellers) {
            String title = element.findElement(By.cssSelector("span.ellip")).getText();
            double price = Double.parseDouble(element
                    .findElement(By.cssSelector("span.price"))
                    .getAttribute("content")
                    .split(" ")[0]
                    .replace(",", "."));
            Bestseller bestseller = new Bestseller(title, price);
            results.add(bestseller);
        }
        return results;
    }

    private void hoverCategory(TopMenuCategory topMenuCategory) {
        waitHelper.waitForVisibilityOfElement(topMenu);
        waitHelper.waitForElementToBeClickable(topMenu);
        String script = "document.querySelectorAll(" +
                "\"li.dropdown\")[" +
                topMenuCategory.ordinal() +
                "].className += ' hover'";
        executeJsScript(script);
        WebElement menuItem = topMenu.findElement(By.linkText(topMenuCategory.getCategoryName()));
        Actions actions = new Actions(driver);
        actions
                .moveToElement(menuItem)
                .build()
                .perform();
    }

    @Step("Click '{subCategory}' in parent '{topMenuCategory}' category")
    public CategoryPage clickSpecificCategory(TopMenuCategory topMenuCategory, String subCategory) {
        hoverCategory(topMenuCategory);
        WebElement element = topMenu.findElement(By.linkText(subCategory));
        element.click();
        return new CategoryPage(driver);
    }
}
