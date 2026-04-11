package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignupPage {

    WebDriver driver;
    WebDriverWait wait;

    By signUpLink = By.id("signin2");
    By username = By.id("sign-username");
    By password = By.id("sign-password");
    By signUpBtn = By.xpath("//button[text()='Sign up']");

    public SignupPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void openSignupModal() {
        wait.until(ExpectedConditions.elementToBeClickable(signUpLink)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(username));
    }

    public void enterCredentials(String user, String pass) {
        driver.findElement(username).clear();
        driver.findElement(username).sendKeys(user);

        driver.findElement(password).clear();
        driver.findElement(password).sendKeys(pass);
    }

    public void clickSignup() {
        driver.findElement(signUpBtn).click();
    }

    public String getAlertTextAndAccept() {
        wait.until(ExpectedConditions.alertIsPresent());
        String msg = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        return msg;
    }
}