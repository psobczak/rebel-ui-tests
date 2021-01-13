package pl.sobczak.pages;

import org.junit.jupiter.api.Test;
import pl.sobczak.models.Bestseller;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

public class HomePageTest extends BasePageTest{

    private HomePage homePage;

    @Test
    public void shouldHave12Bestsellers() {
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
}
