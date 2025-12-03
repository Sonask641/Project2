package ecommerce;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ResponsiveTest extends BaseTest {

    
    @Test
    public void verifyNoHorizontalScrollPositive() {
        
        driver.manage().window().setSize(new Dimension(375, 812)); // iPhone X size

        // Check horizontal scroll
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Long scrollWidth = (Long) js.executeScript("return document.documentElement.scrollWidth;");
        Long clientWidth = (Long) js.executeScript("return document.documentElement.clientWidth;");

        Assert.assertEquals(scrollWidth, clientWidth, "Horizontal scroll exists! Page is not responsive.");
    }

    // Negative scenario: Force a larger width element to simulate horizontal scroll
    
    @Test
    public void verifyHorizontalScrollNegative() {
        
        driver.manage().window().setSize(new Dimension(375, 812));

        JavascriptExecutor js = (JavascriptExecutor) driver;

        
        js.executeScript("document.documentElement.style.overflowX = 'visible';");

        
        js.executeScript(
            "let div = document.createElement('div');" +
            "div.style.width='3000px'; div.style.height='10px';" +
            "document.body.appendChild(div);"
        );

        Long scrollWidth = (Long) js.executeScript("return document.documentElement.scrollWidth;");
        Long clientWidth = (Long) js.executeScript("return document.documentElement.clientWidth;");

        Assert.assertTrue(scrollWidth > clientWidth, "No horizontal scroll detected! Negative test failed.");
    }
}
