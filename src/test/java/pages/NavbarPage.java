package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class NavbarPage {

    WebDriver driver;
    WebDriverWait wait;

    public NavbarPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private By home = By.linkText("Home");
    private By cart = By.id("cartur");
    private By contact = By.linkText("Contact");

    private By contactModal = By.id("exampleModal");
    private By closeBtn = By.xpath("//button[text()='Close']");

    public void openHome() {
        wait.until(ExpectedConditions.elementToBeClickable(home)).click();
    }

    public void openCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cart)).click();
    }

    public void openContact() {
        wait.until(ExpectedConditions.elementToBeClickable(contact)).click();
    }

    public boolean isContactModalDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(contactModal)).isDisplayed();
    }

    public void closeContactModal() {
        wait.until(ExpectedConditions.elementToBeClickable(closeBtn)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(contactModal));
    }

    public boolean isCartOpened() {
        return driver.getCurrentUrl().contains("cart.html");
    }
}