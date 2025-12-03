package ecommerce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginFunctionality extends BaseTest {

    private void openLoginPopup(WebDriverWait wait) {
        driver.findElement(By.id("login2")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername")));
    }

    private String handleAlert(WebDriverWait wait) {
        wait.until(ExpectedConditions.alertIsPresent());
        String alertMsg = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        return alertMsg;
    }

    @Test
    public void loginWithValidCredentials() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        openLoginPopup(wait);

        driver.findElement(By.id("loginusername")).sendKeys("testuser1234");
        driver.findElement(By.id("loginpassword")).sendKeys("testpass123");

        driver.findElement(By.xpath("//button[text()='Log in']")).click();

        String alertMsg = handleAlert(wait);

        Assert.assertEquals(alertMsg, "Wrong password.", 
                "Expected fail because user doesn't exist or password invalid.");
    }

    @Test
    public void loginWithIncorrectPassword() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        openLoginPopup(wait);

        driver.findElement(By.id("loginusername")).sendKeys("existingUser");
        driver.findElement(By.id("loginpassword")).sendKeys("wrongpassword");

        driver.findElement(By.xpath("//button[text()='Log in']")).click();

        String msg = handleAlert(wait);
        Assert.assertEquals(msg, "Wrong password.");
    }

    @Test
    public void loginWithEmptyFields() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        openLoginPopup(wait);

        driver.findElement(By.xpath("//button[text()='Log in']")).click();

        String msg = handleAlert(wait);
        Assert.assertEquals(msg, "Please fill out Username and Password.");
    }

    @Test
    public void loginWithInvalidEmailFormat() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        openLoginPopup(wait);

        driver.findElement(By.id("loginusername")).sendKeys("user.com");
        driver.findElement(By.id("loginpassword")).sendKeys("123");

        driver.findElement(By.xpath("//button[text()='Log in']")).click();

        String msg = handleAlert(wait);
        Assert.assertEquals(msg, "Wrong password.");
    }

    @Test
    public void verifyPasswordMasked() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        openLoginPopup(wait);

        String type = driver.findElement(By.id("loginpassword")).getAttribute("type");
        Assert.assertEquals(type, "password", "Password is NOT masked!");
    }
}

