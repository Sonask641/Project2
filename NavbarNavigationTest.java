package ecommerce;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class NavbarNavigationTest extends BaseTest {

    WebDriverWait wait;

   

 
    @Test(priority = 1, description = "Verify Home link navigates to homepage")
    public void verifyHomeNavigation() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.findElement(By.xpath("//a[contains(text(),'Home')]")).click();

        String url = driver.getCurrentUrl();
        Assert.assertTrue(url.contains("index.html"), "Home navigation failed!");
    }


    @Test(priority = 2, description = "Verify Cart link opens Cart page")
    public void verifyCartNavigation() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.findElement(By.id("cartur")).click(); 
        
        WebElement cartHeader = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'Products')]")));
        
        Assert.assertTrue(cartHeader.isDisplayed(), "Cart page not opened!");
    }

    @Test(priority = 3, description = "Verify Contact link opens contact modal")
    public void verifyContactNavigation() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.findElement(By.xpath("//a[contains(text(),'Contact')]")).click();
        
        WebElement contactModal = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("exampleModal")));
        
        Assert.assertTrue(contactModal.isDisplayed(), "Contact modal not displayed!");
    }


    

    @Test(priority = 4, description = "Negative: Click non-existing navbar link")
    public void verifyInvalidNavbarLink() {
        try {
            driver.findElement(By.linkText("Support")).click(); 
            Assert.fail("Non-existing link was clickable!");
        } catch (NoSuchElementException e) {
            Assert.assertTrue(true, "Properly handled invalid link!");
        }
    }

    @Test(priority = 5, description = "Negative: Verify Contact modal closes properly")
    public void verifyCloseContactModal() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.findElement(By.xpath("//a[contains(text(),'Contact')]")).click();
        
        WebElement closeBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Close')]")));
        closeBtn.click();

        Assert.assertTrue(
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("exampleModal"))),
                "Contact modal did not close!");
    }

    @Test(priority = 6, description = "Negative: Verify Cart page doesn't open without clicking Cart link")
    public void verifyCartNotOpenedWithoutClick() {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertNotEquals(currentUrl, "https://www.demoblaze.com/cart.html",
                "Cart page opened without user action!");
        
    }
        @Test(priority = 7, description = "Negative: Verify no product has missing/broken images")
        public void verifyMissingImageCase() {
            driver.findElement(By.linkText("Phones")).click();
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            
            List<WebElement> images = wait.until(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".card-img-top"))
            );

            JavascriptExecutor js = (JavascriptExecutor) driver;

            for (WebElement img : images) {
                Long width = (Long) js.executeScript("return arguments[0].naturalWidth;", img);
                Assert.assertTrue(width > 0, "Broken product image detected!");
            }
        }

    }
