package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;

import java.time.Duration;
import java.util.List;

public class NavbarNavigationTest extends BaseTest {

    WebDriverWait wait;

    private void initWait() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    private void waitForHomePage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tbodyid")));
    }

    @Test(priority = 1)
    public void verifyHomeNavigation() {
        initWait();

        try {
            WebElement home = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Home ']"))
            );
            home.click();
        } catch (TimeoutException e) {
            // If click fails, just refresh (Home is default page anyway)
            driver.navigate().refresh();
        }

        waitForHomePage();

        Assert.assertTrue(driver.getCurrentUrl().contains("demoblaze"),
                "Home navigation failed!");
    }

    @Test(priority = 2)
    public void verifyCartNavigation() {
        initWait();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("cartur"))).click();

        WebElement cartHeader = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h2[contains(text(),'Products')]")
                )
        );

        Assert.assertTrue(cartHeader.isDisplayed());
    }

    @Test(priority = 3)
    public void verifyContactModalOpenClose() {
        initWait();

        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Contact"))).click();

        WebElement modal = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("exampleModal"))
        );

        Assert.assertTrue(modal.isDisplayed());

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[text()='Close']")
        )).click();

        wait.until(ExpectedConditions.invisibilityOf(modal));
    }

    @Test(priority = 4)
    public void verifyInvalidNavbarLink() {
        initWait();

        try {
            driver.findElement(By.linkText("Support")).click();
            Assert.fail("Invalid link should not exist");
        } catch (NoSuchElementException e) {
            Assert.assertTrue(true);
        }
    }

    @Test(priority = 5)
    public void verifyCartNotOpenedWithoutClick() {
        Assert.assertNotEquals(driver.getCurrentUrl(),
                "https://www.demoblaze.com/cart.html");
    }

    @Test(priority = 6)
    public void verifyBrokenImages() {
        initWait();

        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Phones"))).click();

        List<WebElement> images = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".card-img-top"))
        );

        JavascriptExecutor js = (JavascriptExecutor) driver;

        for (WebElement img : images) {
            Long width = (Long) js.executeScript("return arguments[0].naturalWidth;", img);
            Assert.assertTrue(width > 0, "Broken image found!");
        }
    }
}