package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPlacedPage {

    WebDriver driver;
    WebDriverWait wait;

    public OrderPlacedPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // Locators
    By cartLink = By.id("cartur");
    By placeOrderBtn = By.xpath("//button[text()='Place Order']");

    By name = By.id("name");
    By country = By.id("country");
    By city = By.id("city");
    By card = By.id("card");
    By month = By.id("month");
    By year = By.id("year");

    By purchaseBtn = By.xpath("//button[text()='Purchase']");
    By successMsg = By.xpath("//h2[text()='Thank you for your purchase!']");
    By okBtn = By.xpath("//button[text()='OK']");

    // ✅ FIXED CART CLICK (NO STALE ERROR)
    public void openCart() {
        wait.until(ExpectedConditions.presenceOfElementLocated(cartLink));
        WebElement cart = driver.findElement(cartLink);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", cart);
    }

    public void openPlaceOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(placeOrderBtn)).click();
    }

    public void fillOrderForm(String n, String ctry, String ct, String cc, String m, String y) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(name));

        driver.findElement(name).clear();
        driver.findElement(name).sendKeys(n);

        driver.findElement(country).clear();
        driver.findElement(country).sendKeys(ctry);

        driver.findElement(city).clear();
        driver.findElement(city).sendKeys(ct);

        driver.findElement(card).clear();
        driver.findElement(card).sendKeys(cc);

        driver.findElement(month).clear();
        driver.findElement(month).sendKeys(m);

        driver.findElement(year).clear();
        driver.findElement(year).sendKeys(y);
    }

    public void clickPurchase() {
        wait.until(ExpectedConditions.elementToBeClickable(purchaseBtn)).click();
    }

    public boolean isSuccessDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMsg)).isDisplayed();
    }

    public void closePopup() {
        wait.until(ExpectedConditions.elementToBeClickable(okBtn)).click();
    }
}