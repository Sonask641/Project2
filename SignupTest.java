package ecommerce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.UUID;

public class SignupTest extends BaseTest {

    @Test
    public void signUpWithUniqueCredentials() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        driver.findElement(By.id("signin2")).click(); // Sign-Up button
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sign-username")));

        String uniqueUsername = "user" + UUID.randomUUID().toString().substring(0, 5);

        driver.findElement(By.id("sign-username")).sendKeys(uniqueUsername);
        driver.findElement(By.id("sign-password")).sendKeys("Test@123");

        driver.findElement(By.xpath("//button[text()='Sign up']")).click();

        
        wait.until(ExpectedConditions.alertIsPresent());
        String alertMsg = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();

        Assert.assertEquals(alertMsg, "Sign up successful.", "Sign-Up should succeed for unique username");
    }

    @Test
    public void signUpWithExistingUsername() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        driver.findElement(By.id("signin2")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sign-username")));

        driver.findElement(By.id("sign-username")).sendKeys("existingUser"); 
        driver.findElement(By.id("sign-password")).sendKeys("Test@123");

        driver.findElement(By.xpath("//button[text()='Sign up']")).click();

        wait.until(ExpectedConditions.alertIsPresent());
        String alertMsg = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();

     
        Assert.assertTrue(alertMsg.contains("exist"), "Sign-Up should fail for existing username");
    }


    @Test
    public void signUpWithEmptyFields() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.findElement(By.id("signin2")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sign-username")));

        driver.findElement(By.xpath("//button[text()='Sign up']")).click();

        wait.until(ExpectedConditions.alertIsPresent());
        String alertMsg = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();

        Assert.assertEquals(alertMsg, "Please fill out Username and Password.", "Sign-Up should fail if fields are empty");
    }
}


