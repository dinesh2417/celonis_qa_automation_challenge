package com.trailcloud.testcode;

import com.trailcloud.core.basetest.BaseTest;
import com.trailcloud.core.util.RetryAnalyser;
import com.trailcloud.core.util.TestDataProvider;
import com.trailcloud.core.util.Wait;
import com.trailcloud.pagefactory.AuthenticationPage;
import com.trailcloud.pagefactory.MyAccountPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    @Step("Lauching the browser and enter url")
    public void setUp(@Optional("chrome") String browserName) {
        intialize(browserName);
        openPage(prop.getApplicationPropertyValue("url"));
        authenticationPage = basePage.getInstance(AuthenticationPage.class);
        myAccountPage = basePage.getInstance(MyAccountPage.class);
    }

    @Test(groups = {"healthcheck", "smoke", "authentication"}, dataProvider = "loginTestVaildInput", dataProviderClass = TestDataProvider.class, enabled = true, retryAnalyzer = RetryAnalyser.class)
    @Severity(SeverityLevel.BLOCKER)
    @Description("verify the Login process with valid credential")
    public void signInTest(String username, String password) throws Exception {
        //enter the username, password and click login
        authenticationPage.enterUsername(username);
        authenticationPage.enterPassword(password);
        authenticationPage.clickSignIn();
        Wait.pageLoadTimeoutWait(driver, 5);

        //verify that the user signedIn successfully
        Assert.assertTrue(myAccountPage.isMyAccountButtonDisplayed(), "Failure in signin, my account is not displyed");
        myAccountPage.clickMyAccount();
        Assert.assertTrue(myAccountPage.isEditProfileButtonDisplayed(), "Edit profile link is not displyed");
        Assert.assertTrue(myAccountPage.isSignOutButtonDisplayed(), "Logout button is not displyed");

        //click signout
        myAccountPage.clickSignOut();

        //verify that the user logged out successfully
        Assert.assertTrue(authenticationPage.isSignedOutMessageDisplayed(), "Not properly signed out");

    }

    @Test(groups = {"smoke", "authentication"}, dataProvider = "loginTestInvalidInput", dataProviderClass = TestDataProvider.class, enabled = true, retryAnalyzer = RetryAnalyser.class)
    @Severity(SeverityLevel.BLOCKER)
    @Description("verify the Login process with wrong credential")
    public void verifySigninWithInvalidCredentialsTest(String username, String password) throws Exception {
        //enter the username, password and click login
        authenticationPage.enterUsername(username);
        authenticationPage.enterPassword(password);
        authenticationPage.clickSignIn();

        //verify that the login failure message displayed
        Assert.assertTrue(authenticationPage.isAuthFailedMessageDisplayed(), "SignIn error message not displayed");
        Assert.assertTrue(authenticationPage.getdisplayedAuthFailedMessage().contains("Email or password are incorrect."));
    }
}
