
package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.List;

public class UIUXPage {

    WebDriver driver;
    WebDriverWait wait;

    public UIUXPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Navigate to homepage
    public void openHomePage() {
        driver.get("https://www.demoblaze.com/");
    }

    // Product tiles
    public List<WebElement> getProductTiles() {
        return wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.cssSelector(".card.h-100")
                )
        );
    }

    // Navbar elements
    public WebElement getCartButton() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("cartur"))
        );
    }

    public WebElement getContactButton() {
        return driver.findElement(By.linkText("Contact"));
    }

    public WebElement getAboutUsButton() {
        return driver.findElement(By.linkText("About us"));
    }

    // First product click
    public void openFirstProduct() {
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector(".card-title a")
                )
        ).click();
    }

    // Add to cart
    public void clickAddToCart() {
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[text()='Add to cart']")
                )
        ).click();
    }

    // Alert handling
    public String getAlertTextAndAccept() {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String text = alert.getText();
        alert.accept();
        return text;
    }

    // Negative element check
    public List<WebElement> getFakeElement() {
        return driver.findElements(By.id("fakeButton123"));
    }

    // Font check
    public String getBodyFont() {
        return driver.findElement(By.tagName("body"))
                .getCssValue("font-family");
    }

    // Product images
    public List<WebElement> getProductImages() {
        return wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.cssSelector(".card-img-top")
                )
        );
    }

    public long getImageNaturalWidth(WebElement img) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (Long) js.executeScript("return arguments[0].naturalWidth;", img);
    }
}