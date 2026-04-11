package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class CartTest {

    WebDriver driver;
    WebDriverWait wait;
    String baseUrl = "https://www.demoblaze.com";

    @BeforeClass
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.get(baseUrl);
    }

    public void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public void login() {
        click(By.id("login2"));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername")))
                .sendKeys("sona8467");

        driver.findElement(By.id("loginpassword")).sendKeys("sonask8467");

        click(By.xpath("//button[text()='Log in']"));

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("logInModal")));
    }

    public void openFirstProduct() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@id='tbodyid']//h4/a")
        )).click();
    }

    @Test(priority = 1)
    public void addSingleProductToCart() {

        login();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@id='tbodyid']//h4/a")
        )).click();

        click(By.xpath("//a[text()='Add to cart']"));

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(), "Product added.");
        alert.accept();
    }

    @Test(priority = 2)
    public void viewCart() {

        click(By.id("cartur"));

        List<WebElement> products = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//tr/td[2]"))
        );

        Assert.assertTrue(products.size() > 0, "Cart is empty!");
    }

    @Test(priority = 3)
    public void removeProductFromCart() {

        
        driver.get(baseUrl);

       
        wait.until(ExpectedConditions.elementToBeClickable(By.id("cartur"))).click();

       
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));

        try {
            WebElement deleteBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Delete']"))
            );

            deleteBtn.click();
            System.out.println("Product deleted successfully");

        } catch (TimeoutException e) {
            Assert.fail("No product found in cart to delete");
        }
    }

    @Test(priority = 4)
    public void addMultipleProductsToCart() {

        driver.get(baseUrl);

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@id='tbodyid']//h4/a")
        )).click();

        click(By.xpath("//a[text()='Add to cart']"));
        wait.until(ExpectedConditions.alertIsPresent()).accept();

        driver.get(baseUrl);

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@id='tbodyid']//h4/a")
        )).click();

        click(By.xpath("//a[text()='Add to cart']"));
        wait.until(ExpectedConditions.alertIsPresent()).accept();

        click(By.id("cartur"));

        List<WebElement> products = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//tr/td[2]"))
        );

        Assert.assertTrue(products.size() >= 2, "Less than 2 products in cart");
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}


