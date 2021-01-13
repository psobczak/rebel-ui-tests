package pl.sobczak.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class CategoryPage extends BasePage {

    @FindBy(css = "main#content > h1")
    private WebElement categoryName;

    @FindBy(css = "main#content > div:first-of-type")
    private WebElement categoryDescription;

    public CategoryPage(EventFiringWebDriver driver) {
        super(driver);
    }

    public String getCategoryName() {
        return categoryName.getText();
    }

    public String getCategoryDescription() {
        return categoryDescription.getText();
    }
}
