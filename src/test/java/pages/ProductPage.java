package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {

    WebDriver driver;
    WebDriverWait wait;

    By phones = By.linkText("Phones");
    By laptops = By.linkText("Laptops");
    By monitors = By.linkText("Monitors");

    By firstProduct = By.xpath("//*[@id='tbodyid']/div[1]/div/div/h4/a");
    By secondProduct = By.xpath("//*[@id='tbodyid']/div[2]/div/div/h4/a");

    By addToCart = By.xpath("//a[text()='Add to cart']");

    public ProductPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void openPhones() {
        wait.until(ExpectedConditions.elementToBeClickable(phones)).click();
    }

    public void openLaptops() {
        wait.until(ExpectedConditions.elementToBeClickable(laptops)).click();
    }

    public void openMonitors() {
        wait.until(ExpectedConditions.elementToBeClickable(monitors)).click();
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
}