package ecommerce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class Logoutfunctionality extends BaseTest {

    private void loginUser(WebDriverWait wait, String username, String password) {
        driver.findElement(By.id("login2")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername")));

        driver.findElement(By.id("loginusername")).sendKeys("sona8467");
        driver.findElement(By.id("loginpassword")).sendKeys("sonask8467");

        driver.findElement(By.xpath("//button[text()='Log in']")).click();

        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout2")));
    }

    @Test
    public void verifyLogoutPositive() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        
        loginUser(wait, "existingUser", "correctPassword");

       
        WebElement logoutBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("logout2")));
        logoutBtn.click();

        
        wait.until(ExpectedConditions.urlContains("demoblaze.com"));
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("demoblaze.com"),
                "User not redirected to home page after logout!");

      
        WebElement loginBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login2")));
        Assert.assertTrue(loginBtn.isDisplayed(), "Login button not visible after logout");
    }

    
    @Test
    public void verifyLogoutNegative() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        
        WebElement logoutBtn = driver.findElement(By.id("logout2"));

        
        boolean isVisible = false;
        try {
            wait.until(ExpectedConditions.visibilityOf(logoutBtn));
            isVisible = true;
        } catch (Exception e) {
            
            isVisible = false;
        }

        Assert.assertFalse(isVisible, "Logout button should NOT be visible when not logged in");
    }
}