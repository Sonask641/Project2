package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.SignupPage;

import java.time.Duration;
import java.util.UUID;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignupTest extends BaseTest {

    @Test
    public void signUpWithUniqueCredentials() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        SignupPage signup = new SignupPage(driver, wait);

        String uniqueUser = "user" + UUID.randomUUID().toString().substring(0, 6);

        signup.openSignupModal();
        signup.enterCredentials(uniqueUser, "Test@123");
        signup.clickSignup();

        String alertMsg = signup.getAlertTextAndAccept();

        Assert.assertEquals(alertMsg, "Sign up successful.");
    }

    @Test
    public void signUpWithExistingUsername() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        SignupPage signup = new SignupPage(driver, wait);

        signup.openSignupModal();
        signup.enterCredentials("existingUser", "Test@123");
        signup.clickSignup();

        String alertMsg = signup.getAlertTextAndAccept();

        Assert.assertTrue(alertMsg.toLowerCase().contains("exist"));
    }

    @Test
    public void signUpWithEmptyFields() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        SignupPage signup = new SignupPage(driver, wait);

        signup.openSignupModal();
        signup.clickSignup();

        String alertMsg = signup.getAlertTextAndAccept();

        Assert.assertTrue(alertMsg.contains("fill out"));
    }
}
