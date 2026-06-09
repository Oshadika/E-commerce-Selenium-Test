import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CartTest {

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
    public void TC_CART_01_VerifyCartPageDisplay() {

        driver.findElement(
                        By.cssSelector("[data-test='add-to-cart-sauce-labs-backpack']"))
                .click();

        driver.findElement(By.className("shopping_cart_link"))
                .click();

        Assert.assertTrue(driver.getCurrentUrl().contains("cart"));

        Assert.assertTrue(
                driver.findElement(By.className("cart_item"))
                        .isDisplayed());
    }

    @Test
    public void TC_CART_02_RemoveProductFromCartPage() {

        driver.findElement(
                        By.cssSelector("[data-test='add-to-cart-sauce-labs-backpack']"))
                .click();

        driver.findElement(By.className("shopping_cart_link"))
                .click();

        driver.findElement(
                        By.cssSelector("[data-test='remove-sauce-labs-backpack']"))
                .click();

        List<WebElement> items =
                driver.findElements(By.className("cart_item"));

        Assert.assertEquals(items.size(), 0);
    }

    @Test
    public void TC_CART_03_ContinueShoppingButton() {

        driver.findElement(
                        By.cssSelector("[data-test='add-to-cart-sauce-labs-backpack']"))
                .click();

        driver.findElement(By.className("shopping_cart_link"))
                .click();

        driver.findElement(
                        By.cssSelector("[data-test='continue-shopping']"))
                .click();

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
    }

    @Test
    public void TC_CART_04_CheckoutButton() {

        driver.findElement(
                        By.cssSelector("[data-test='add-to-cart-sauce-labs-backpack']"))
                .click();

        driver.findElement(By.className("shopping_cart_link"))
                .click();

        driver.findElement(
                        By.cssSelector("[data-test='checkout']"))
                .click();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("checkout-step-one"));
    }

    @Test
    public void TC_CART_05_CartPersistence() {

        driver.findElement(
                        By.cssSelector("[data-test='add-to-cart-sauce-labs-backpack']"))
                .click();

        driver.findElement(By.className("shopping_cart_link"))
                .click();

        driver.findElement(
                        By.cssSelector("[data-test='continue-shopping']"))
                .click();

        driver.findElement(By.className("shopping_cart_link"))
                .click();

        List<WebElement> items =
                driver.findElements(By.className("cart_item"));

        Assert.assertEquals(items.size(), 1);
    }

    @Test
    public void TC_PRODUCT_07_VerifySortingFeature() {

        Select sortDropdown = new Select(
                driver.findElement(
                        By.cssSelector("[data-test='product_sort_container']"))
        );

        sortDropdown.selectByVisibleText("Price (low to high)");

        List<WebElement> priceElements =
                driver.findElements(By.className("inventory_item_price"));

        List<Double> actualPrices = new ArrayList<>();

        for (WebElement price : priceElements) {
            actualPrices.add(
                    Double.parseDouble(
                            price.getText().replace("$", "")
                    )
            );
        }

        List<Double> expectedPrices =
                new ArrayList<>(actualPrices);

        Collections.sort(expectedPrices);

        Assert.assertEquals(actualPrices, expectedPrices);
    }
}
