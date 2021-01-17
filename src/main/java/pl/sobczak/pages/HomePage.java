package pl.sobczak.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import pl.sobczak.models.Bestseller;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePage {

    @FindBy(css = "input.ui-autocomplete-input")
    private WebElement searchBar;

    @FindBy(css = "div.products--small div.product__wrapper")
    private List<WebElement> bestsellers;

    @CacheLookup
    @FindBy(css = "div.menu div.container-fluid")
    private WebElement topMenu;

    public HomePage(EventFiringWebDriver driver) {
        super(driver);
        driver.get("https://www.rebel.pl/");
    }

    public HomePage typeIntoSearchBar(String text) {
        searchBar.clear();
        searchBar.sendKeys(text);
        return this;
    }

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
        WebElement menuItem = topMenu.findElement(By.linkText(topMenuCategory.getCategoryName()));
        Actions actions = new Actions(driver);
        actions.moveToElement(menuItem)
                .build()
                .perform();
    }

    public CategoryPage clickSpecificCategory(TopMenuCategory topMenuCategory, String subCategory) {
        hoverCategory(topMenuCategory);
        WebElement element = topMenu.findElement(By.linkText(subCategory));
        Actions actions = new Actions(driver);
        actions.moveToElement(element)
                .click()
                .build()
                .perform();
        return new CategoryPage(driver);
    }
}
