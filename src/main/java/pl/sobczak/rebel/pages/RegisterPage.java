package pl.sobczak.rebel.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import pl.sobczak.rebel.models.User;

public class RegisterPage extends BasePage {

    @CacheLookup
    @FindBy(css = "input#register_name")
    private WebElement registerName;

    @CacheLookup
    @FindBy(css = "input#register_email")
    private WebElement registerEmail;

    @CacheLookup
    @FindBy(css = "input#register_password")
    private WebElement registerPassword;

    @CacheLookup
    @FindBy(css = "input#register_password_confirmation")
    private WebElement registerPasswordConfirmation;

    @CacheLookup
    @FindBy(css = "button#register_submit")
    private WebElement registerSubmit;

    public RegisterPage(EventFiringWebDriver driver) {
        super(driver);
    }

    public RegisterPage typeUsername(String username) {
        clearAndType(registerName, username);
        return this;
    }

    public RegisterPage typeEmail(String email) {
        clearAndType(registerEmail, email);
        return this;
    }

    public RegisterPage typePassword(String password) {
        clearAndType(registerPassword, password);
        return this;
    }

    public RegisterPage typePasswordConfirmation(String passwordConfirmation) {
        clearAndType(registerPasswordConfirmation, passwordConfirmation);
        return this;
    }

    public void registerUser(User user) {
        typeUsername(user.getUsername());
        typeEmail(user.getEmail());
        typePassword(user.getPassword());
        typePasswordConfirmation(user.getRepeatedPassword());
    }
}
