package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By loginBtn = By.id("login2");
    private By username = By.id("loginusername");
    private By password = By.id("loginpassword");
    private By submitBtn = By.xpath("//button[text()='Log in']");
    private By logoutBtn = By.id("logout2");

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void openLoginPopup() {
        driver.findElement(loginBtn).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(username));
    }

    public void enterUsername(String user) {
        driver.findElement(username).clear();
        driver.findElement(username).sendKeys(user);
    }

    public void enterPassword(String pass) {
        driver.findElement(password).clear();
        driver.findElement(password).sendKeys(pass);
    }

    public void clickLogin() {
        driver.findElement(submitBtn).click();
    }

    public void login(String user, String pass) {
        openLoginPopup();
        enterUsername(user);
        enterPassword(pass);
        clickLogin();
    }

    public String getAlertText() {
        wait.until(ExpectedConditions.alertIsPresent());
        String text = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        return text;
    }

    public String getPasswordType() {
        return driver.findElement(password).getAttribute("type");
    }

    public boolean isLogoutVisible() {
        return driver.findElement(logoutBtn).isDisplayed();
    }
}