package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.OrderPlacedPage;

import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPlaced extends BaseTest {

    @Test
    public void placeOrderSuccessfully() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  
        // Login
        LoginPage login = new LoginPage(driver, wait);
        login.login("sona8467", "sonask8467");

        // Order flow
        OrderPlacedPage order = new OrderPlacedPage(driver, wait);

        order.openCart();
        order.openPlaceOrder();

        order.fillOrderForm(
                "Test User",
                "India",
                "Coimbatore",
                "4111222233334444",
                "11",
                "2028"
        );

        order.clickPurchase();

        Assert.assertTrue(order.isSuccessDisplayed(), "Order success message not displayed");

        order.closePopup();
    }
}