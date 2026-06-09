import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ProductTest {

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

    @Test
    public void TC_PRODUCT_01_VerifyProductListDisplay() {

        List<WebElement> products =
                driver.findElements(By.className("inventory_item"));

        Assert.assertTrue(products.size() > 0);
    }

    @Test
    public void TC_PRODUCT_02_VerifyProductDetails() {

        List<WebElement> products =
                driver.findElements(By.className("inventory_item"));

        for (WebElement product : products) {

            Assert.assertTrue(
                    product.findElement(By.className("inventory_item_name"))
                            .isDisplayed());

            Assert.assertTrue(
                    product.findElement(By.className("inventory_item_price"))
                            .isDisplayed());

            Assert.assertTrue(
                    product.findElement(By.tagName("img"))
                            .isDisplayed());
        }
    }

    @Test
    public void TC_PRODUCT_03_AddProductToCart() {

        driver.findElement(
                        By.cssSelector("[data-test='add-to-cart-sauce-labs-backpack']"))
                .click();

        String cartCount =
                driver.findElement(By.className("shopping_cart_badge"))
                        .getText();

        Assert.assertEquals(cartCount, "1");

        Assert.assertTrue(
                driver.findElement(
                                By.cssSelector("[data-test='remove-sauce-labs-backpack']"))
                        .isDisplayed());
    }

    @Test
    public void TC_PRODUCT_04_RemoveProductFromProductPage() {

        driver.findElement(
                        By.cssSelector("[data-test='add-to-cart-sauce-labs-backpack']"))
                .click();

        driver.findElement(
                        By.cssSelector("[data-test='remove-sauce-labs-backpack']"))
                .click();

        List<WebElement> badge =
                driver.findElements(By.className("shopping_cart_badge"));

        Assert.assertEquals(badge.size(), 0);
    }

    @Test
    public void TC_PRODUCT_05_AddMultipleProducts() {

        List<WebElement> addButtons =
                driver.findElements(
                        By.cssSelector("[data-test^='add-to-cart']"));

        for (WebElement button : addButtons) {
            button.click();
        }

        String count =
                driver.findElement(By.className("shopping_cart_badge"))
                        .getText();

        Assert.assertTrue(Integer.parseInt(count) > 1);
    }

    @Test
    public void TC_PRODUCT_06_NavigateToCartPage() {

        driver.findElement(
                        By.cssSelector("[data-test='add-to-cart-sauce-labs-backpack']"))
                .click();

        driver.findElement(By.className("shopping_cart_link"))
                .click();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("cart"));

        List<WebElement> cartItems =
                driver.findElements(By.className("cart_item"));

        Assert.assertTrue(cartItems.size() > 0);
    }
}