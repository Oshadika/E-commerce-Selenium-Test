import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {

        WebDriver driver;

        @BeforeMethod
        public void setUp() {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("https://www.saucedemo.com/");
        }

        @AfterMethod
        public void tearDown() {
            driver.quit();
        }

        @Test
        public void TC_LOGIN_01_ValidLogin() {

            driver.findElement(By.id("user-name"))
                    .sendKeys("standard_user");

            driver.findElement(By.id("password"))
                    .sendKeys("secret_sauce");

            driver.findElement(By.id("login-button"))
                    .click();

            Assert.assertTrue(
                    driver.getCurrentUrl().contains("inventory.html")
            );

            String title = driver.findElement(By.className("title"))
                    .getText();

            Assert.assertEquals(title, "Products");
        }

        @Test
        public void TC_LOGIN_02_InvalidLogin() {

            driver.findElement(By.id("user-name"))
                    .sendKeys("wrong_user");

            driver.findElement(By.id("password"))
                    .sendKeys("wrong_pass");

            driver.findElement(By.id("login-button"))
                    .click();

            String error = driver.findElement(
                    By.cssSelector("[data-test='error']")
            ).getText();

            Assert.assertTrue(
                    error.contains("Username and password do not match")
            );
        }

        @Test
        public void TC_LOGIN_03_EmptyPassword() {

            driver.findElement(By.cssSelector("[data-test='username']"))
                    .sendKeys("standard_user");

            driver.findElement(By.id("login-button"))
                    .click();

            String error = driver.findElement(
                    By.cssSelector("[data-test='error']")
            ).getText();

            Assert.assertTrue(
                    error.contains("Password is required")
            );
        }

        @Test
        public void TC_LOGIN_04_EmptyUsername() {

            driver.findElement(By.id("password"))
                    .sendKeys("secret_sauce");

            driver.findElement(By.id("login-button"))
                    .click();

            String error = driver.findElement(
                    By.cssSelector("[data-test='error']")
            ).getText();

            Assert.assertTrue(
                    error.contains("Username is required")
            );
        }

        @Test
        public void TC_LOGIN_05_EmptyLogin() {

            driver.findElement(By.id("login-button"))
                    .click();

            String error = driver.findElement(
                    By.cssSelector("[data-test='error']")
            ).getText();

            Assert.assertTrue(
                    error.contains("Username is required")
            );
        }
    }

