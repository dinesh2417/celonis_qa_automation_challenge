package com.trailcloud.testcode;

import com.trailcloud.core.basetest.BaseTest;
import com.trailcloud.core.util.Wait;
import com.trailcloud.pagefactory.*;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

public class ProcessExplorerTest extends BaseTest {

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


    @Test(groups = {"component", "processexplorer"},
            enabled = true)
    @Severity(SeverityLevel.CRITICAL)
    @Description("verify the Activites Section functionalities")
    public void checkActivitiesSectionFunctionalityTest() throws Exception {
        //click 'PurchaseToPay' workspace
        homePage.clickWorkspace(purchaseToPayWorkspace);

        //click 'PurchaseToPay' analysis link
        purchaseToPay.clickAnalysisLink();
        Wait.pageLoadTimeoutWait(driver, 5);

        //verify the default Activities chart percentage
        Wait.implicitWait(driver, 2);
        Double defaultChartPercentage = Double.parseDouble(processExplorer.getActivitiesSectionChartPercentage()
                .substring(0, processExplorer.getActivitiesSectionChartPercentage().length() - 1));
        int defaultCanvasNodeCount = processExplorer.getCanvasNodesCount();
        assertThat(defaultChartPercentage, is(greaterThan(90.0)));

        //click 'More' button and verify the increase in Activities chart percentage
        processExplorer.clickActivitiesSectionMoreButton(2);
        Wait.implicitWait(driver, 2);
        processExplorer.clickCanvas();
        Double chartPercentageAfterAddingSteps = Double.parseDouble(processExplorer.getActivitiesSectionChartPercentage()
                .substring(0, processExplorer.getActivitiesSectionChartPercentage().length() - 1));
        assertThat(chartPercentageAfterAddingSteps, is(greaterThan(defaultChartPercentage)));

        //verify the increase in node in the canvas
        processExplorer.clickCanvas();
        int canvasNodeCountAfterAddingSteps = processExplorer.getCanvasNodesCount();
        assertThat(canvasNodeCountAfterAddingSteps, is(greaterThan(defaultCanvasNodeCount)));
    }

    @Test(groups = {"component", "processexplorer"},
            enabled = true)
    @Severity(SeverityLevel.CRITICAL)
    @Description("erify the Connection Section functionalities")
    public void checkConnectionSectionFunctionalityTest() throws Exception {
        //click 'PurchaseToPay' workspace
        homePage.clickWorkspace(purchaseToPayWorkspace);

        //click 'PurchaseToPay' analysis link
        purchaseToPay.clickAnalysisLink();
        Wait.pageLoadTimeoutWait(driver, 5);

        //verify the default Connections chart percentage
        Wait.implicitWait(driver, 2);
        Double defaultChartPercentage = Double.parseDouble(processExplorer.getConnectionsSectionChartPercentage().substring(0, processExplorer.getConnectionsSectionChartPercentage().length() - 1));
        int defaultCanvasEdgeCount = processExplorer.getCanvasConnectionEdgeCount();
        assertThat(defaultChartPercentage, is(greaterThan(80.0)));

        //click 'More' button and verify the increase in Connections chart percentage
        processExplorer.clickConnectionsSectionMoreButton(2);
        processExplorer.clickConnectionsSectionSlider();
        Double chartPercentageAfterAddingSteps = Double.parseDouble(processExplorer.getConnectionsSectionChartPercentage().substring(0, processExplorer.getConnectionsSectionChartPercentage().length() - 1));
        assertThat(chartPercentageAfterAddingSteps, is(greaterThan(defaultChartPercentage)));

        //verify the increase in connection edges in the canvas
        processExplorer.clickCanvas();
        int canvasEdgeCountAfterAddingSteps = processExplorer.getCanvasConnectionEdgeCount();
        assertThat(canvasEdgeCountAfterAddingSteps, is(greaterThan(defaultCanvasEdgeCount)));
    }

}
