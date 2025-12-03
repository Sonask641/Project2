package ecommerce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;


public class OrderPlaced extends BaseTest {

   
    private final By initialPlaceOrderButton = By.xpath("//button[text()='Place Order']");
    private final By nameField = By.id("name");
    private final By creditCardField = By.id("card");
    private final By purchaseButton = By.xpath("//button[text()='Purchase']");
    private final By successMessageTitle = By.xpath("//h2[text()='Thank you for your purchase!']");
    private final By orderDetailsText = By.xpath("//p[@class='lead text-muted']");  
    private final By okButton = By.xpath("//button[text()='OK']");
    private final By loginLink = By.id("login2");
    private final By usernameField = By.id("loginusername");
    private final By passwordField = By.id("loginpassword");
    private final By loginButton = By.xpath("//button[text()='Log in']");
    private final By welcomeUser = By.id("nameofuser");
    private final By samsungS6 = By.partialLinkText("Samsung galaxy"); 
    private final By addToCartButton = By.xpath("//a[text()='Add to cart']");
    private final By cartLink = By.id("cartur");

    // -login-
    private void login() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.findElement(loginLink).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        driver.findElement(usernameField).sendKeys("sona8467"); 
        driver.findElement(passwordField).sendKeys("sonask8467"); 
        driver.findElement(loginButton).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeUser));
        System.out.println("Status: User logged in successfully as " + driver.findElement(welcomeUser).getText());
    }

    // - Add Product -
    private void addProductToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.findElement(samsungS6).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartButton));
        driver.findElement(addToCartButton).click();

        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        System.out.println("Status: Product added to cart.");
    }

    @Test
    public void testPlaceOrderWithValidDetailsAndVerifyConfirmation() {

        login();
        addProductToCart();

        driver.findElement(cartLink).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(ExpectedConditions.elementToBeClickable(initialPlaceOrderButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameField));

        driver.findElement(nameField).sendKeys("Test User");
        driver.findElement(By.id("country")).sendKeys("USA");
        driver.findElement(By.id("city")).sendKeys("New York");
        driver.findElement(creditCardField).sendKeys("4111222233334444");
        driver.findElement(By.id("month")).sendKeys("11");
        driver.findElement(By.id("year")).sendKeys("2028");

        driver.findElement(purchaseButton).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessageTitle));

     
        String confirmationText = driver.findElement(By.xpath("//p[contains(text(),'Id:')]")).getText();
        Assert.assertTrue(confirmationText.contains("Id:"), "Test Failed: Missing Order ID.");

        System.out.println("✔ POSITIVE TEST PASSED. Confirmation Details:\n" + confirmationText);

        driver.findElement(okButton).click();
    }

    
    @Test
    public void testPlaceOrderWithEmptyForm() {

        login();
        addProductToCart();

        driver.findElement(cartLink).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(ExpectedConditions.elementToBeClickable(initialPlaceOrderButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameField));

        driver.findElement(purchaseButton).click();

        String alertText = "";
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            alertText = driver.switchTo().alert().getText();
            driver.switchTo().alert().accept();
        } catch (Exception e) {
            Assert.fail("Negative Test Failed: Expected browser alert did not appear.");
        }

        Assert.assertEquals(alertText, "Please fill out Name and Creditcard.",
                "Negative Test Failed: Expected alert text was incorrect.");

        Assert.assertFalse(isElementPresent(successMessageTitle),
                "Negative Test Failed: Successful order dialog appeared unexpectedly.");

        System.out.println("✔ NEGATIVE TEST PASSED. Correct alert received: " + alertText);
    }

   
    private boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }
}
