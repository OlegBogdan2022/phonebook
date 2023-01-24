import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class FirstSeleniumTest {
    WebDriver driver;
    By emailField = By.cssSelector("[placeholder=\"Email\"]");
    By passwordField = By.cssSelector("[placeholder=\"Password\"]");
    By confirm_password = By.cssSelector("[ng-reflect-name=\"confirm_password\"]");


    // before

    @BeforeClass
    public static void setup() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setupTest() {
        driver = new ChromeDriver();
        driver.get("http://phonebook.telran-edu.de:8080/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }
    // test

    @Test
    public void loginWithValidData() {
        driver.findElement(By.name("email")).sendKeys("test3@gmail.com");
        driver.findElement(By.cssSelector("[placeholder=\"Password\"]")).sendKeys("test3@gmail.com");
        WebElement element = driver.findElement(By.cssSelector("[placeholder=\"Confirm Password\"]"));
        driver.findElement(By.cssSelector(".btn.btn-info"));


    }

    @Test
    public void registerNewUser() {
        String userData = "test3@gmail.com";
        driver.findElement(By.id("login-form")).isDisplayed();
        driver.findElement(By.cssSelector("[href=\"/user/registration\"]")).click();
        driver.findElement(By.id("registartion-form")).isDisplayed();
        fillfield(userData, emailField);
        fillfield(userData, passwordField);
        fillfield(userData, confirm_password);
        driver.findElement(By.xpath("//*[@type=\"submit\"]")).click();
    }

    private void fillfield(String userData, By cssSelector) {
        driver.findElement(cssSelector).sendKeys(userData);
        driver.findElement(cssSelector).click();
    }
    // after

    @AfterMethod
    public void tearDown() throws InterruptedException {
        if (driver != null) {
            Thread.sleep(500);
            driver.quit();
        }
    }
}
