package tests;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.ResponsivePage;

public class ResponsiveTest extends BaseTest {

    ResponsivePage rp;

    // 📱 Mobile responsiveness check
    @Test(priority = 1)
    public void verifyMobileResponsiveness() {

        rp = new ResponsivePage(driver);

        driver.manage().window().setSize(new Dimension(375, 812));
        rp.openHomePage();

        Assert.assertTrue(rp.getScrollWidth() <= rp.getClientWidth(),
                "Mobile view has horizontal scroll!");
    }

    // 📟 Tablet responsiveness check
    @Test(priority = 2)
    public void verifyTabletResponsiveness() {

        rp = new ResponsivePage(driver);

        driver.manage().window().setSize(new Dimension(768, 1024));
        rp.openHomePage();

        Assert.assertTrue(rp.getScrollWidth() <= rp.getClientWidth(),
                "Tablet view has horizontal scroll!");
    }

    // 🖥️ Desktop responsiveness check
    @Test(priority = 3)
    public void verifyDesktopResponsiveness() {

        rp = new ResponsivePage(driver);

        driver.manage().window().setSize(new Dimension(1920, 1080));
        rp.openHomePage();

        Assert.assertTrue(rp.getScrollWidth() <= rp.getClientWidth(),
                "Desktop view has horizontal scroll!");
    }

    @Test(priority = 4)
    public void verifyForcedOverflowScenario() {

        rp = new ResponsivePage(driver);

        driver.manage().window().setSize(new Dimension(375, 812));
        rp.openHomePage();

        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Inject overflow element
        js.executeScript(
                "let div = document.createElement('div');" +
                "div.id='testOverflow';" +
                "div.style.width='3000px';" +
                "div.style.height='10px';" +
                "document.body.appendChild(div);"
        );

        // Force browser reflow (IMPORTANT FIX)
        js.executeScript("document.body.offsetHeight;");

        // Wait a small time for layout update
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    }
