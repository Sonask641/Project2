package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class ResponsivePage {

    WebDriver driver;

    public ResponsivePage(WebDriver driver) {
        this.driver = driver;
    }

    public void openHomePage() {
        driver.get("https://www.demoblaze.com/");
    }

    public long getScrollWidth() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (Long) js.executeScript("return document.documentElement.scrollWidth;");
    }

    public long getClientWidth() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (Long) js.executeScript("return document.documentElement.clientWidth;");
    }
}