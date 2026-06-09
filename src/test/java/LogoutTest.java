import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LogoutTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.id("user-name"))
                .sendKeys("standard_user");

        driver.findElement(By.id("password"))
                .sendKeys("secret_sauce");

        driver.findElement(By.id("login-button"))
                .click();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("inventory"));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void TC_LOGOUT_01_VerifyLogout() {

        driver.findElement(
                        By.id("react-burger-menu-btn"))
                .click();

        driver.findElement(
                        By.id("logout_sidebar_link"))
                .click();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("saucedemo.com"));

        Assert.assertTrue(
                driver.findElement(By.id("login-button"))
                        .isDisplayed());
    }
}
