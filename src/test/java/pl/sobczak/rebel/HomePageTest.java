package pl.sobczak.rebel;

import org.testng.annotations.Test;
import pl.sobczak.rebel.models.Bestseller;
import pl.sobczak.rebel.pages.CategoryPage;
import pl.sobczak.rebel.pages.HomePage;
import pl.sobczak.rebel.pages.TopMenuCategory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class HomePageTest extends BasePageTest {

    private HomePage homePage;

    @Test
    public void shouldContainsBestseller() {
        homePage = new HomePage(driver);
        List<Bestseller> bestsellers = homePage.getBestsellers();
        assertThat(bestsellers)
                .extracting("title", "price")
                .contains(tuple("Pandemic Legacy: Sezon 2 (edycja żółta)", 298.95));
    }

    @Test
    public void shouldDisplayCorrectCategoryNameAndDescription() {
        CategoryPage categoryPage = new HomePage(driver)
                .clickSpecificCategory(TopMenuCategory.AKCESORIA, "Różne");

        assertThat(categoryPage.getCategoryName()).isEqualTo("Różne");
        assertThat(categoryPage.getCategoryDescription())
                .isEqualTo("Nawet nie wiedziałeś, że takie rzeczy istnieją... Jak się mogłeś do tej pory bez nich obyć? ;)");
    }

    @Test
    public void sampleTest() throws InterruptedException {
        homePage = new HomePage(driver);
        homePage.clickToolbarAccountButton();
        Thread.sleep(3000);
    }
}
