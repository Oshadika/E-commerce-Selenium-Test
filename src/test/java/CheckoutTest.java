import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class CheckoutTest {

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

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    private void addProductToCart() {

        driver.findElement(
                        By.cssSelector("[data-test='add-to-cart-sauce-labs-backpack']"))
                .click();

        driver.findElement(By.className("shopping_cart_link"))
                .click();
    }

    @Test
    public void TC_CHECKOUT_01_NavigateToCheckoutPage() {

        addProductToCart();

        driver.findElement(
                        By.cssSelector("[data-test='checkout']"))
                .click();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("checkout-step-one"));

        Assert.assertTrue(
                driver.findElement(By.className("checkout_info"))
                        .isDisplayed());
    }

    @Test
    public void TC_CHECKOUT_02_ValidateRequiredFields() {

        addProductToCart();

        driver.findElement(
                        By.cssSelector("[data-test='checkout']"))
                .click();

        driver.findElement(
                        By.cssSelector("[data-test='continue']"))
                .click();

        String error =
                driver.findElement(
                                By.cssSelector("[data-test='error']"))
                        .getText();

        Assert.assertTrue(
                error.contains("First Name is required"));
    }

    @Test
    public void TC_CHECKOUT_03_EnterValidCheckoutInformation() {

        addProductToCart();

        driver.findElement(
                        By.cssSelector("[data-test='checkout']"))
                .click();

        driver.findElement(
                        By.cssSelector("[data-test='firstName']"))
                .sendKeys("John");

        driver.findElement(
                        By.cssSelector("[data-test='lastName']"))
                .sendKeys("Doe");

        driver.findElement(
                        By.cssSelector("[data-test='postalCode']"))
                .sendKeys("12345");

        driver.findElement(
                        By.cssSelector("[data-test='continue']"))
                .click();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("checkout-step-two"));

        Assert.assertTrue(
                driver.findElement(By.className("cart_item"))
                        .isDisplayed());

        Assert.assertTrue(
                driver.findElement(
                                By.className("summary_total_label"))
                        .isDisplayed());
    }

    @Test
    public void TC_CHECKOUT_04_VerifyOrderSummary() {

        addProductToCart();

        driver.findElement(
                        By.cssSelector("[data-test='checkout']"))
                .click();

        driver.findElement(
                        By.cssSelector("[data-test='firstName']"))
                .sendKeys("John");

        driver.findElement(
                        By.cssSelector("[data-test='lastName']"))
                .sendKeys("Doe");

        driver.findElement(
                        By.cssSelector("[data-test='postalCode']"))
                .sendKeys("12345");

        driver.findElement(
                        By.cssSelector("[data-test='continue']"))
                .click();

        List<WebElement> items =
                driver.findElements(By.className("cart_item"));

        Assert.assertTrue(items.size() > 0);

        Assert.assertTrue(
                driver.findElement(
                                By.className("summary_subtotal_label"))
                        .isDisplayed());

        Assert.assertTrue(
                driver.findElement(
                                By.className("summary_total_label"))
                        .isDisplayed());
    }

    @Test
    public void TC_CHECKOUT_05_CompleteCheckout() {

        addProductToCart();

        driver.findElement(
                        By.cssSelector("[data-test='checkout']"))
                .click();

        driver.findElement(
                        By.cssSelector("[data-test='firstName']"))
                .sendKeys("John");

        driver.findElement(
                        By.cssSelector("[data-test='lastName']"))
                .sendKeys("Doe");

        driver.findElement(
                        By.cssSelector("[data-test='postalCode']"))
                .sendKeys("12345");

        driver.findElement(
                        By.cssSelector("[data-test='continue']"))
                .click();

        driver.findElement(
                        By.cssSelector("[data-test='finish']"))
                .click();

        String message =
                driver.findElement(
                                By.className("complete-header"))
                        .getText();

        Assert.assertEquals(
                message,
                "Thank you for your order!");
    }

    @Test
    public void TC_CHECKOUT_06_CancelCheckout() {

        addProductToCart();

        driver.findElement(
                        By.cssSelector("[data-test='checkout']"))
                .click();

        driver.findElement(
                        By.cssSelector("[data-test='cancel']"))
                .click();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("cart"));

        List<WebElement> items =
                driver.findElements(By.className("cart_item"));

        Assert.assertTrue(items.size() > 0);
    }

    @Test
    public void TC_CHECKOUT_07_InvalidPostalCode() {

        addProductToCart();

        driver.findElement(
                        By.cssSelector("[data-test='checkout']"))
                .click();

        driver.findElement(
                        By.cssSelector("[data-test='firstName']"))
                .sendKeys("John");

        driver.findElement(
                        By.cssSelector("[data-test='lastName']"))
                .sendKeys("Doe");

        driver.findElement(
                        By.cssSelector("[data-test='postalCode']"))
                .sendKeys("ABCDE");

        driver.findElement(
                        By.cssSelector("[data-test='continue']"))
                .click();

        // SauceDemo accepts alphabetic postal codes
        Assert.assertTrue(
                driver.getCurrentUrl().contains("checkout-step-two"));
    }

    @Test
    public void TC_CHECKOUT_08_CheckoutWithoutCartItems() {

        driver.findElement(
                        By.className("shopping_cart_link"))
                .click();

        driver.findElement(
                        By.cssSelector("[data-test='checkout']"))
                .click();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("checkout-step-one"));
    }
}
