package pl.sobczak.rebel.pages;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import pl.sobczak.rebel.models.Bestseller;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class HomePage extends BasePage {

    @FindBy(css = "div.products--small div.product__wrapper")
    private List<WebElement> bestsellers;

    public HomePage(EventFiringWebDriver driver) {
        super(driver);
        driver.get("https://www.rebel.pl/");
    }

    @Step("Click toolbar account button")
    public HomePage clickToolbarAccountButton() {
        waitAndClick(getHeader().getToolbarAccountButton());
        return this;
    }

    @Step("Click register button")
    public RegisterPage clickRegisterButton() {
        Actions actions = new Actions(driver);
        actions.moveToElement(getHeader().getRegisterButton())
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
        waitHelper.waitForVisibilityOfElement(getHeader().getTopMenu());
        waitHelper.waitForElementToBeClickable(getHeader().getTopMenu());
        String script = "document.querySelectorAll(" +
                "\"li.dropdown\")[" +
                topMenuCategory.ordinal() +
                "].className += ' hover'";
        executeJsScript(script);
        WebElement menuItem = getHeader().getTopMenu().findElement(By.linkText(topMenuCategory.getCategoryName()));
        Actions actions = new Actions(driver);
        actions
                .moveToElement(menuItem)
                .build()
                .perform();
    }

    @Step("Click '{subCategory}' in parent '{topMenuCategory}' category")
    public CategoryPage clickSpecificCategory(TopMenuCategory topMenuCategory, String subCategory) {
        hoverCategory(topMenuCategory);
        WebElement element = getHeader().getTopMenu().findElement(By.linkText(subCategory));
        element.click();
        return new CategoryPage(driver);
    }
}
