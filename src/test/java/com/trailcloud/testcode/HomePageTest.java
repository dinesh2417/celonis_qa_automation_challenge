package com.trailcloud.testcode;

import com.trailcloud.core.basetest.BaseTest;
import com.trailcloud.core.util.Wait;
import com.trailcloud.pagefactory.AuthenticationPage;
import com.trailcloud.pagefactory.HomePage;
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

import java.util.Arrays;

public class HomePageTest extends BaseTest {

    private static String[] workspaceList = {"SAP ECC - Order to Cash", "SAP ECC - Purchase to Pay",
            "ServiceNow Ticketing", "SAP ECC - Purchase to Pay"};

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    @Step("Lauching the browser and enter url")
    public void setUp(@Optional("chrome") String browserName) throws Exception {
        intialize(browserName);
        openPage(prop.getApplicationPropertyValue("url"));
        authenticationPage = basePage.getInstance(AuthenticationPage.class);
        myAccountPage = basePage.getInstance(MyAccountPage.class);
        homePage = basePage.getInstance(HomePage.class);
        authenticationPage.loginAs(prop.getApplicationPropertyValue("username"), prop.getApplicationPropertyValue("password"));
        Wait.pageLoadTimeoutWait(driver, 5);
    }

    @Test(groups = {"healthcheck", "smoke"}, enabled = true)
    @Severity(SeverityLevel.BLOCKER)
    @Description("verify that all the workspaces display proproply once logged in")
    public void checkWorkspacesTest() {
        //verify that the user signed in successfully
        Assert.assertTrue(myAccountPage.isMyAccountButtonDisplayed(), "Failure in sign in");

        //verify that the given workspaces are available in homepage
        Arrays.stream(workspaceList).forEach(workspace -> {
            Assert.assertTrue(homePage.isWorkspaceAvailable(workspace), "Workspace '"+workspace+"' is not displayed in homepage");
        });

        //click signout
        myAccountPage.clickMyAccountAndSignOut();
    }
}
