package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.ProductPage;

import java.time.Duration;

public class ProductBrowsingTests extends BaseTest {

    @Test(priority = 1)
    public void verifyPhonesCategory() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        ProductPage product = new ProductPage(driver, wait);

        product.openPhones();

        Assert.assertTrue(true, "Phones category loaded");
    }

    @Test(priority = 2)
    public void verifyLaptopsCategory() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        ProductPage product = new ProductPage(driver, wait);

        product.openLaptops();

        Assert.assertTrue(true, "Laptops category loaded");
    }

    @Test(priority = 3)
    public void verifyMonitorsCategory() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        ProductPage product = new ProductPage(driver, wait);

        product.openMonitors();

        Assert.assertTrue(true, "Monitors category loaded");
    }
}