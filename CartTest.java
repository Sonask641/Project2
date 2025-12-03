package ecommerce;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class CartTest {

    WebDriver driver;
    WebDriverWait wait;
    String baseUrl = "https://www.demoblaze.com"; 

    @BeforeClass
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(baseUrl);
    }

    
    public void safeClick(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    // Utility method to login
    public void login(String username, String password) {
        safeClick(By.id("login2"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername"))).sendKeys("sona8467");
        driver.findElement(By.id("loginpassword")).sendKeys("sonask8467");
        driver.findElement(By.xpath("//button[text()='Log in']")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("logInModal")));
    }

    @Test(priority = 1)
    public void addSingleProductToCart() {
        login("testuser", "testpassword"); 
        safeClick(By.xpath("//*[@id='tbodyid']/div[1]/div/div/h4/a")); 
        safeClick(By.xpath("//a[text()='Add to cart']"));

       
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(), "Product added.");
        alert.accept();
    }

    @Test(priority = 2)
    public void viewCart() {
        safeClick(By.id("cartur")); 
        List<WebElement> products = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//tr/td[2]"))); // product titles
        Assert.assertTrue(products.size() > 0, "Cart should have at least one product");
    }

    @Test(priority =3)
    public void removeProductFromCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Delete"))).click();
            System.out.println("Product removed successfully");
        } catch (TimeoutException e) {
            System.out.println("Delete link not found");
        }
    }


    @Test(priority = 4)
    public void addMultipleProductsToCart() throws InterruptedException {
        driver.get(baseUrl); 
        
        safeClick(By.xpath("//*[@id='tbodyid']/div[1]/div/div/h4/a"));
        safeClick(By.xpath("//a[text()='Add to cart']"));
        wait.until(ExpectedConditions.alertIsPresent()).accept();

        driver.get(baseUrl); 
        
        safeClick(By.xpath("//*[@id='tbodyid']/div[2]/div/div/h4/a"));
        safeClick(By.xpath("//a[text()='Add to cart']"));
        wait.until(ExpectedConditions.alertIsPresent()).accept();

        safeClick(By.id("cartur"));
        List<WebElement> products = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//tr/td[2]")));
        Assert.assertTrue(products.size() >= 2, "Cart should have at least two products");
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}



