package pl.sobczak.rebel.pages;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import pl.sobczak.rebel.config.exceptions.ProductNotFoundException;
import pl.sobczak.rebel.models.CategoryPageItem;

import java.util.ArrayList;
import java.util.List;

public class CategoryPage extends BasePage {

    @FindBy(css = "main#content > h1")
    private WebElement categoryName;

    @FindBy(css = "main#content > div:first-of-type")
    private WebElement categoryDescription;

    @FindBy(css = "div#search-results > div")
    private List<WebElement> items;

    public CategoryPage(EventFiringWebDriver driver) {
        super(driver);
    }

    public String getCategoryName() {
        return categoryName.getText();
    }

    public String getCategoryDescription() {
        return categoryDescription.getText();
    }

    @Step("Get items from page")
    public List<CategoryPageItem> getItems() {
        List<CategoryPageItem> categoryPageItems = new ArrayList<>();
        waitHelper.waitForVisibilityOfElements(items);
        for (WebElement item : items) {
            categoryPageItems.add(getItem(item));
        }
        Allure.addAttachment("Number of found items", String.valueOf(categoryPageItems.size()));
        return categoryPageItems;
    }

    @Step("Try to find item '{itemName}'")
    public CategoryPageItem getItem(String itemName) {
        CategoryPageItem categoryPageItem = null;
        waitHelper.waitForVisibilityOfElements(items);
        for (WebElement item : items) {
            if (item.findElement(By.cssSelector("h3.product__title span.ellip.ellip-line"))
                    .getText()
                    .trim()
                    .equals(itemName)) {
                categoryPageItem = getItem(item);
                break;
            } else {
                throw new ProductNotFoundException("Product \"" + itemName + "\" could not be found on page " + driver.getCurrentUrl());
            }
        }

        if (categoryPageItem != null) {
            Allure.addAttachment("Item details", categoryPageItem.toString());
        }
        return categoryPageItem;
    }

    private CategoryPageItem getItem(WebElement item) {
        List<String> tags = getTagsFromItem(item);
        String path = getTextWithCss(item, "div.product__short-desc-wrapper  div.product__path");
        String title = getTextWithCss(item, "h3.product__title span.ellip.ellip-line");
        int stars = item
                .findElements(By.cssSelector("div.product__short-desc-wrapper li.review-stars__star--filled"))
                .size();
        int comments = Integer.parseInt(
                item
                        .findElement(By.cssSelector("div.product__short-desc-wrapper span.review-stars__counter"))
                        .getText()
                        .replace("(", "")
                        .replace(")", "")
        );
        String deliveryDescription = getTextWithCss(
                item, "div.product__short-desc-wrapper div.product__delivery");
        double currentPrice = getPrice(item, "div.product__short-desc-wrapper div.d-none span.product__price-wrapper span.product__price");
        double oldPrice = getPrice(item, "div.product__short-desc-wrapper div.d-none span.product__price-wrapper span.product__price--old");

        return new CategoryPageItem(
                tags, path, title, stars, comments, deliveryDescription, currentPrice, oldPrice);
    }

    private boolean itemContainsTags(WebElement element) {
        return element.findElements(By.cssSelector("div.product__label")).size() > 0;
    }

    private List<String> getTagsFromItem(WebElement element) {
        List<String> listOfTags = new ArrayList<>();
        if (itemContainsTags(element)) {
            for (WebElement tag : element.findElements(By.cssSelector("div.product__label"))) {
                listOfTags.add(tag.getText());
            }
        }

        return listOfTags;
    }

    private double getPrice(WebElement item, String cssSelector) {
        String price;
        try {
            price = item.findElement(By.cssSelector(cssSelector))
                    .getText()
                    .trim()
                    .split(" ")[0];
            int dotIndex = price.length() - 2;
            price = price.substring(0, dotIndex) + "." + price.substring(dotIndex);
        } catch (NoSuchElementException e) {
            price = "0";
        }
        return Double.parseDouble(price);
    }
}
