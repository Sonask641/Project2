package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;

import java.time.Duration;

import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginFunctionality extends BaseTest {

    @Test
    public void loginWithValidCredentials() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        LoginPage login = new LoginPage(driver, wait);

        login.login("testuser1234", "testpass123");

        String alertMsg = login.getAlertText();
        Assert.assertEquals(alertMsg, "Wrong password.");
    }

    @Test
    public void loginWithIncorrectPassword() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        LoginPage login = new LoginPage(driver, wait);

        login.login("existingUser", "wrongpassword");

        Assert.assertEquals(login.getAlertText(), "Wrong password.");
    }

    @Test
    public void loginWithEmptyFields() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        LoginPage login = new LoginPage(driver, wait);

        login.login("", "");

        Assert.assertEquals(login.getAlertText(),
                "Please fill out Username and Password.");
    }

    @Test
    public void loginWithInvalidEmailFormat() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        LoginPage login = new LoginPage(driver, wait);

        login.login("user.com", "123");

        Assert.assertEquals(login.getAlertText(), "Wrong password.");
    }

    @Test
    public void verifyPasswordMasked() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        LoginPage login = new LoginPage(driver, wait);

        login.openLoginPopup();

        Assert.assertEquals(login.getPasswordType(), "password");
    }
}

