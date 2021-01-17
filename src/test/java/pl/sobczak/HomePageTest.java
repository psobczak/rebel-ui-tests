package pl.sobczak;

import org.testng.annotations.Test;
import pl.sobczak.models.Bestseller;
import pl.sobczak.models.CategoryPageItem;
import pl.sobczak.pages.CategoryPage;
import pl.sobczak.pages.HomePage;
import pl.sobczak.pages.TopMenuCategory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class HomePageTest extends BasePageTest{

    private HomePage homePage;

    @Test
    public void shouldHave12Bestsellers() {
        homePage = new HomePage(driver);
        List<Bestseller> bestsellers = homePage.getBestsellers();
        assertThat(bestsellers)
                .extracting("title", "price")
                .contains(tuple("Pandemic Legacy: Sezon 2 (edycja żółta)", 298.96));
    }

    @Test
    public void shouldDisplayCorrectCategoryNameAndDescription() throws InterruptedException {
        CategoryPage categoryPage = new HomePage(driver)
                .clickSpecificCategory(TopMenuCategory.AKCESORIA, "Różne");

        assertThat(categoryPage.getCategoryName()).isEqualTo("Różne");
        assertThat(categoryPage.getCategoryDescription())
                .isEqualTo("Nawet nie wiedziałeś, że takie rzeczy istnieją... Jak się mogłeś do tej pory bez nich obyć? ;)");
    }

    @Test
    public void categoryItemTest() throws InterruptedException {
        CategoryPage categoryPage = new HomePage(driver)
                .clickSpecificCategory(TopMenuCategory.KSIAZKI_I_KOMIKSY, "Bestsellery");
        List<CategoryPageItem> items = categoryPage.getItems();
        assertThat(items.size()).isEqualTo(19);
    }

    @Test
    public void sampleTest() throws InterruptedException {
        CategoryPage categoryPage = new HomePage(driver)
                .clickSpecificCategory(TopMenuCategory.KSIAZKI_I_KOMIKSY, "Bestsellery");
        CategoryPageItem categoryPageItem = categoryPage.getItem("Anioł Exterminatus");
        assertThat(categoryPageItem.getStars()).isEqualTo(0);
    }

}
