package ecommerce;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class ProductBrowsingTests extends BaseTest {

    WebDriverWait wait;

    public void initWait() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }


    
    @Test(priority = 1, description = "Verify Phones category products are displayed")
    public void verifyPhonesCategory() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement phonesLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Phones")));
        phonesLink.click();

        
        List<WebElement> products = wait.until(ExpectedConditions
                .presenceOfAllElementsLocatedBy(By.cssSelector(".card-title")));

        Assert.assertTrue(products.size() > 0, "Phones products not displayed");
    }

  
    @Test(priority = 2, description = "Verify Laptops category products are displayed")
    public void verifyLaptopsCategory() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement laptopsLink = wait.until(
                ExpectedConditions.elementToBeClickable(By.linkText("Laptops")));
        laptopsLink.click();

        
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("tbodyid")));

        List<WebElement> products = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".card-title")));

        Assert.assertTrue(products.size() > 0, "Laptops products not displayed");
    }


  
    @Test(priority = 3, description = "Verify product detail page opens on clicking product")
   
    public void verifyProductDetailPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        By product = By.xpath("//a[contains(text(),'Samsung galaxy s6')]");

        
        WebElement productElement = wait.until(ExpectedConditions.elementToBeClickable(product));

       
        productElement.click();

     
        WebElement productHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[contains(text(),'Samsung galaxy s6')]")));

        Assert.assertTrue(productHeader.isDisplayed(), "Product detail page did not open!");
    }



    @Test(priority = 4, description = "Verify Home navigation displays all products")
    public void verifyHomeNavigation() {
        initWait();
        driver.findElement(By.xpath("//a[contains(text(),'Home')]")).click();

        List<WebElement> products =
                wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".card-title")));

        Assert.assertTrue(products.size() > 0, "❌ Home page products NOT visible!");
    }


    

    @Test(priority = 5, description = "Negative: Click non-existing category link")
    public void verifyInvalidCategoryHandling() {
        try {
            driver.findElement(By.linkText("Tablets")).click(); 
            Assert.fail("❌ Invalid category clicked unexpectedly!");
        } catch (NoSuchElementException e) {
            Assert.assertTrue(true, "✔️ Application handled missing category correctly");
        }
    }

    @Test(priority = 6, description = "Negative: Blank area click should not navigate")
    public void verifyBlankAreaClickDoesNothing() {
        initWait();
        String beforeURL = driver.getCurrentUrl();

        WebElement blankSpace = driver.findElement(By.id("contcont"));
        blankSpace.click();

        Assert.assertEquals(driver.getCurrentUrl(), beforeURL,
                "❌ URL changed after blank space click – unexpected navigation!");
    }

    @Test(priority = 7, description = "Negative: Verify no product has missing/broken images")
    public void verifyMissingImageCase() {
        initWait();
        driver.findElement(By.linkText("Phones")).click();

        List<WebElement> images =
                wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".card-img-top")));

        Assert.assertTrue(images.size() > 0, "❌ No product images found in Phones category!");

        JavascriptExecutor js = (JavascriptExecutor) driver;

        for (WebElement img : images) {
            Long width = (Long) js.executeScript("return arguments[0].naturalWidth;", img);
            Assert.assertTrue(width > 0, "❌ Broken image detected for product: " + img.getAttribute("src"));
        }
    }
}


