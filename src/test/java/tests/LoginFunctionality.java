package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utils.ExcelUtils;

import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginFunctionality extends BaseTest {

    private LoginPage login;

    @BeforeMethod
    public void initPage() {
        login = new LoginPage(driver, new WebDriverWait(driver, Duration.ofSeconds(10)));
    }

    @Test
    public void loginWithIncorrectPassword() {
        String username = ExcelUtils.getCellData("Login", 1, 1);
        String password = ExcelUtils.getCellData("Login", 1, 2);
        String expectedAlert = ExcelUtils.getCellData("Login", 1, 3);

        login.login(username, password);

        Assert.assertEquals(
                login.getAlertText().trim().replaceAll("\\s+", " "),
                expectedAlert.trim().replaceAll("\\s+", " ")
        );
    }

    @Test
    public void loginWithEmptyFields() {
        login.login("", "");
        Assert.assertEquals(
                login.getAlertText().trim(),
                "Please fill out Username and Password."
        );
    }

    @Test
    public void loginWithInvalidUsername() {
        login.login("user.com", "123");
        Assert.assertEquals(
                login.getAlertText().trim(),
                "Wrong password."
        );
    }

    @Test
    public void verifyPasswordMasked() {
        login.openLoginPopup();
        Assert.assertEquals(login.getPasswordType(), "password");
    }
}