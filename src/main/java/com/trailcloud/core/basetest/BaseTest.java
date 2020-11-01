package com.trailcloud.core.basetest;

import com.trailcloud.core.config.ConfigPropertyLoader;
import com.trailcloud.core.driver.DriverFactory;
import com.trailcloud.core.listener.WebEventListener;
import com.trailcloud.core.util.TestUtil;
import com.trailcloud.core.util.Wait;
import com.trailcloud.pagefactory.*;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;

import static java.util.Objects.nonNull;

@Listeners(com.trailcloud.core.listener.TestListener.class)
@Slf4j
public class BaseTest {

    protected AccountCreationPage accountCreationPage;
    protected AuthenticationPage authenticationPage;
    protected HomePage homePage;
    protected MyAccountPage myAccountPage;
    protected PurchaseToPay purchaseToPay;
    protected ProcessExplorer processExplorer;

    public static WebDriver driver;
    public static ConfigPropertyLoader prop;
    private static EventFiringWebDriver event_driver;
    private static WebEventListener event_listener;
    public static BasePage basePage;

    public BaseTest() {
        this.prop = ConfigPropertyLoader.getInstance();
    }

    public void intialize(String browser) {
        driver = new DriverFactory(browser).getDriver();
        event_driver = new EventFiringWebDriver(driver);
        event_listener = new WebEventListener();
        event_driver.register(event_listener);
        driver = event_driver;

        basePage = new BasePage(driver);
        driver.manage().window().maximize();

        log.info("Deleting all the cookies");
        TestUtil.deleteAllCookies();
    }

    public void openPage(String url) {
        log.info("Entering URL into the browser");
        driver.get(url);
        Wait.pageLoadTimeoutWait(driver, 20);
        Wait.implicitWait(driver, 20);
    }

    public WebDriver getDriver() {
        return driver;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownDriver() {
        log.info("Closing the browser");
        if (nonNull(driver)) {
            driver.quit();
            driver = null;
        }
        log.info("Browser closed");
    }
}
