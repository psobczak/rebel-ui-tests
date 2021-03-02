package pl.sobczak.rebel;

import org.testng.annotations.Test;
import pl.sobczak.rebel.pages.CategoryPage;
import pl.sobczak.rebel.pages.HomePage;

import static org.assertj.core.api.Assertions.assertThatCode;

public class SearchTest extends BasePageTest {

    private HomePage homePage;
    private CategoryPage categoryPage;

    @Test
    public void searchFunctionShouldFindItem() {
        homePage = new HomePage(driver);
        categoryPage = homePage
                .getHeader()
                .search("Pandemia");

        assertThatCode(() -> categoryPage.getItem("Pandemia: Laboratorium")).doesNotThrowAnyException();
    }
}
