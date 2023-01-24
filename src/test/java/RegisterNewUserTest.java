import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterNewUserTest extends TestBase {


    By loginForm = By.id("login-form");

    By userRegistrationLink = By.cssSelector("[href=\"/user/registration\"]");

    By registrationForm = By.id("registration-form");
    By emailField = By.cssSelector("[placeholder=\"Email\"]");

    By passwordFiled = By.cssSelector("[placeholder=\"Password\"]");

    By confirmPasswordField = By.cssSelector("[ng-reflect-name=\"confirm_password\"]");

    By loginButton = By.xpath("//*[@type=\"submit\"]");

    By errorMessageBlock = By.id("error-message");


    Faker faker = new Faker();

    @Test
    public void registerNewUser() {
        String userData = faker.internet().emailAddress();
        String expectedErrorMessage = "noErrorMsg";

        driver.findElement(loginForm).isDisplayed();
        driver.findElement(userRegistrationLink).click();
        driver.findElement(registrationForm).isDisplayed();
        fillField(userData, emailField);
        fillField(userData, passwordFiled);
        fillField(userData, confirmPasswordField);
        driver.findElement(loginButton).click();
        String actualErrorMessage = driver.findElement(errorMessageBlock).getText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }
}

