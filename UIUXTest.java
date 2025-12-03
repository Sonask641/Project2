package ecommerce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class UIUXTest extends BaseTest {

    @Test
    public void verifyProductTilesAlignment() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> products = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".card.h-100"))
        );
        Assert.assertTrue(products.size() > 0, "No product tiles found!");
    }

    @Test
    public void verifyActionButtonsVisibility() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement cartBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cartur")));
        WebElement contactBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[data-target='#exampleModal']")));
        WebElement aboutUsBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[data-target='#videoModal']")));

        Assert.assertTrue(cartBtn.isDisplayed(), "Cart button not visible!");
        Assert.assertTrue(contactBtn.isDisplayed(), "Contact button not visible!");
        Assert.assertTrue(aboutUsBtn.isDisplayed(), "About us button not visible!");
    }

    @Test
    public void verifyAlertStyling() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        
        WebElement firstProduct = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".card.h-100 .card-title a")));
        firstProduct.click();

        WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn-success")));
        addToCartBtn.click();

        
        wait.until(ExpectedConditions.alertIsPresent());
        String alertText = driver.switchTo().alert().getText();
        Assert.assertTrue(alertText.contains("Product added"), "Alert text mismatch!");
        driver.switchTo().alert().accept();

        driver.navigate().back(); 
    }

    @Test
    public void verifyMissingButtonNegative() {
        List<WebElement> missingElements = driver.findElements(By.id("nonExistingButton"));
        Assert.assertTrue(missingElements.isEmpty(), "Unexpected button found!");
    }

    @Test
    public void verifyFontConsistency() {
        WebElement body = driver.findElement(By.tagName("body"));
        String font = body.getCssValue("font-family");
        Assert.assertTrue(font.toLowerCase().contains("roboto") || font.toLowerCase().contains("lato"),
                "Font family mismatch! Found: " + font);
    }
}


