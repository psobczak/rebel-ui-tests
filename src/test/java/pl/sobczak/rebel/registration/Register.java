package pl.sobczak.rebel.registration;

import org.testng.annotations.Test;
import pl.sobczak.rebel.BasePageTest;
import pl.sobczak.rebel.models.User;
import pl.sobczak.rebel.pages.HomePage;
import pl.sobczak.rebel.pages.RegisterPage;

public class Register extends BasePageTest {

    private HomePage homePage;
    private RegisterPage registerPage;

    @Test
    private void registerUser() {
        User user = User.createFakeUser();
        homePage = new HomePage(driver);
        registerPage = homePage
                .clickToolbarAccountButton()
                .clickRegisterButton();
        registerPage.registerUser(user);
    }
}
