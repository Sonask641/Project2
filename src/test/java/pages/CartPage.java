package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {

    WebDriver driver;
    WebDriverWait wait;

    By cartBtn = By.id("cartur");
    By products = By.xpath("//tr/td[2]");
    By deleteBtn = By.linkText("Delete");

    public CartPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void openCart() {
        driver.findElement(cartBtn).click();
    }

    public int getCartItems() {
        return driver.findElements(products).size();
    }

    public void deleteProduct() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteBtn)).click();
    }
}