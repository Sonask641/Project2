package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {

    WebDriver driver;
    WebDriverWait wait;

    // Categories (more stable XPath than linkText)
    By phones = By.xpath("//a[normalize-space()='Phones']");
    By laptops = By.xpath("//a[normalize-space()='Laptops']");
    By monitors = By.xpath("//a[normalize-space()='Monitors']");

    // Products
    By firstProduct = By.xpath("//*[@id='tbodyid']/div[1]/div/div/h4/a");
    By secondProduct = By.xpath("//*[@id='tbodyid']/div[2]/div/div/h4/a");

    // Add to cart
    By addToCart = By.xpath("//a[text()='Add to cart']");

    public ProductPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void openPhones() {
        wait.until(ExpectedConditions.elementToBeClickable(phones)).click();
        waitForPageLoad();
    }

    public void openLaptops() {
        wait.until(ExpectedConditions.elementToBeClickable(laptops)).click();
        waitForPageLoad();
    }

    public void openMonitors() {
        wait.until(ExpectedConditions.elementToBeClickable(monitors)).click();
        waitForPageLoad();
    }

    public void openFirstProduct() {
        wait.until(ExpectedConditions.elementToBeClickable(firstProduct)).click();
    }

    public void openSecondProduct() {
        wait.until(ExpectedConditions.elementToBeClickable(secondProduct)).click();
    }

    public void addToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCart)).click();
    }

    public void acceptAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    // 🔥 Stability helper (VERY IMPORTANT)
    private void waitForPageLoad() {
        try {
            Thread.sleep(1000); // helps DOM refresh after category click
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}