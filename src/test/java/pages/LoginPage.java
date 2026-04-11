package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;

    // Locators
    By loginBtn = By.id("login2");
    By username = By.id("loginusername");
    By password = By.id("loginpassword");
    By submitBtn = By.xpath("//button[text()='Log in']");

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // Open login popup
    public void openLoginPopup() {
        driver.findElement(loginBtn).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(username));
    }

    // Enter credentials
    public void enterUsername(String user) {
        driver.findElement(username).sendKeys(user);
    }

    public void enterPassword(String pass) {
        driver.findElement(password).sendKeys(pass);
    }

    // Click login
    public void clickLogin() {
        driver.findElement(submitBtn).click();
    }

    // Full login flow
    public void login(String user, String pass) {
        openLoginPopup();
        enterUsername(user);
        enterPassword(pass);
        clickLogin();
    }

    // Handle alert
    public String getAlertText() {
        wait.until(ExpectedConditions.alertIsPresent());
        String text = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        return text;
    }

    // Check password masked
    public String getPasswordType() {
        return driver.findElement(password).getAttribute("type");
    }
}