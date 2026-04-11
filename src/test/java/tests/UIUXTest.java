package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;

import base.BaseTest;
import pages.UIUXPage;

import java.util.List;

public class UIUXTest extends BaseTest {

    UIUXPage ui;

    // 1️⃣ Product tiles presence test
    @Test(priority = 1)
    public void verifyProductTilesPresence() {
        ui = new UIUXPage(driver);

        ui.openHomePage();

        List<WebElement> products = ui.getProductTiles();

        Assert.assertTrue(products.size() > 0, "No product tiles found!");
    }

    // 2️⃣ Navbar UI visibility test
    @Test(priority = 2)
    public void verifyNavbarUIElements() {
        ui = new UIUXPage(driver);

        ui.openHomePage();

        Assert.assertTrue(ui.getCartButton().isDisplayed(), "Cart button not visible!");
        Assert.assertTrue(ui.getContactButton().isDisplayed(), "Contact button not visible!");
        Assert.assertTrue(ui.getAboutUsButton().isDisplayed(), "About Us button not visible!");
    }

    // 3️⃣ Add to cart UI alert test
    @Test(priority = 3)
    public void verifyAddToCartAlert() {
        ui = new UIUXPage(driver);

        ui.openHomePage();

        ui.openFirstProduct();
        ui.clickAddToCart();

        String alertText = ui.getAlertTextAndAccept();

        Assert.assertTrue(alertText.contains("Product added"),
                "Alert message mismatch!");
    }

    // 4️⃣ Negative UI element check
    @Test(priority = 4)
    public void verifyNonExistingUIElement() {
        ui = new UIUXPage(driver);

        ui.openHomePage();

        List<WebElement> fake = ui.getFakeElement();

        Assert.assertTrue(fake.isEmpty(), "Unexpected UI element found!");
    }

    @Test(priority = 5)
    public void verifyFontConsistency() {
        ui = new UIUXPage(driver);

        ui.openHomePage();

        String font = ui.getBodyFont().toLowerCase();

        // More realistic + flexible validation
        Assert.assertTrue(
                font.contains("lato") ||
                font.contains("arial") ||
                font.contains("helvetica") ||
                font.contains("sans-serif"),
                "Unexpected font found: " + font
        );
    }

    // 6️⃣ Image load validation test
    @Test(priority = 6)
    public void verifyProductImagesLoad() {
        ui = new UIUXPage(driver);

        ui.openHomePage();

        List<WebElement> images = ui.getProductImages();

        for (WebElement img : images) {
            long width = ui.getImageNaturalWidth(img);

            Assert.assertTrue(width > 0, "Broken product image detected!");
        }
    }
}


