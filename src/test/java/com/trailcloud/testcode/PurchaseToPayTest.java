package com.trailcloud.testcode;

import com.trailcloud.core.basetest.BaseTest;
import com.trailcloud.core.util.Wait;
import com.trailcloud.pagefactory.*;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PurchaseToPayTest extends BaseTest {

    private static String purchaseToPayWorkspace = "SAP ECC - Purchase to Pay";

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    @Step("Lauching the browser and enter url")
    public void setUp(@Optional("chrome") String browserName) throws Exception {
        intialize(browserName);
        openPage(prop.getApplicationPropertyValue("url"));
        authenticationPage = basePage.getInstance(AuthenticationPage.class);
        homePage = basePage.getInstance(HomePage.class);
        myAccountPage = basePage.getInstance(MyAccountPage.class);
        purchaseToPay = basePage.getInstance(PurchaseToPay.class);
        processExplorer = basePage.getInstance(ProcessExplorer.class);
        authenticationPage.loginAs(prop.getApplicationPropertyValue("username"), prop.getApplicationPropertyValue("password"));
        Wait.pageLoadTimeoutWait(driver, 5);
    }

    @Test(groups = {"smoke", "component", "purchasetopay"},
            enabled = true)
    @Severity(SeverityLevel.CRITICAL)
    @Description("verify the content of the Process Explorer page")
    public void checkProcessExplorerPageContentTest() {
        //click 'PurchaseToPay' workspace
        homePage.clickWorkspace(purchaseToPayWorkspace);

        //click 'PurchaseToPay' analysis link
        purchaseToPay.clickAnalysisLink();
        Wait.pageLoadTimeoutWait(driver, 5);

        //verify the 'Process Explorer' page content
        Assert.assertTrue(processExplorer.isConnectionsSectionChartIsDisplayed(), "Connections Section chart is not displayed properly");
        Assert.assertTrue(processExplorer.isConnectionsSectionSliderIsDisplayed(), "Connections Section slider is not displayed properly");
        Assert.assertTrue(processExplorer.isActivitiesSectionChartIsDisplayed(), "Activities Section chart is not displayed properly");
        Assert.assertTrue(processExplorer.isActivitiesSectionSliderIsDisplayed(), "Activities Section slider is not displayed properly");
    }
}
