package com.trailcloud.core.listener;

import com.trailcloud.core.basetest.BaseTest;
import com.trailcloud.core.util.TestUtil;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

import java.io.IOException;

/**
 * WebEventListener Class implements WebDriverEventListener which contains all the Webdriver logs
 */
@Slf4j
public class WebEventListener extends BaseTest implements WebDriverEventListener {

    @Override
    public void beforeNavigateTo(String url, WebDriver webDriver) {
        log.info("Navigating to url: '" + url.toString() + "'");
    }

    @Override
    public void afterNavigateTo(String url, WebDriver webDriver) {
        log.info("Opened the url: '" + url.toString() + "'");
    }

    @Override
    public void beforeClickOn(WebElement webElement, WebDriver webDriver) {
        log.info("Try clicking :'" + webElement.toString() + "'");

    }

    @Override
    public void afterClickOn(WebElement webElement, WebDriver webDriver) {
        log.info("Clicked on :'" + webElement.toString() + "'");
    }

    @Override
    public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {
        log.info("Value of the:" + webElement.toString() + "before changes made");
    }

    @Override
    public void afterChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {
        log.info("Value of the:" + webElement.toString() + "after changes made");
    }

    @Override
    public void onException(Throwable throwable, WebDriver webDriver) {
        log.error("Exception occured:" + throwable.toString());
        try {
            String screenshotPath = TestUtil.takeScreenshot();
            log.info("Screenshot taken and stored as : '" + screenshotPath + "'");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void beforeScript(String s, WebDriver webDriver) {
    }

    @Override
    public void afterScript(String s, WebDriver webDriver) {

    }

    @Override
    public void beforeSwitchToWindow(String s, WebDriver webDriver) {

    }

    @Override
    public void afterSwitchToWindow(String s, WebDriver webDriver) {

    }

    @Override
    public <X> void beforeGetScreenshotAs(OutputType<X> outputType) {

    }

    @Override
    public <X> void afterGetScreenshotAs(OutputType<X> outputType, X x) {

    }

    @Override
    public void beforeGetText(WebElement webElement, WebDriver webDriver) {

    }

    @Override
    public void afterGetText(WebElement webElement, WebDriver webDriver, String s) {

    }

    @Override
    public void beforeAlertAccept(WebDriver webDriver) {

    }

    @Override
    public void afterAlertAccept(WebDriver webDriver) {

    }

    @Override
    public void afterAlertDismiss(WebDriver webDriver) {

    }

    @Override
    public void beforeAlertDismiss(WebDriver webDriver) {

    }


    @Override
    public void beforeNavigateBack(WebDriver webDriver) {

    }

    @Override
    public void afterNavigateBack(WebDriver webDriver) {

    }

    @Override
    public void beforeNavigateForward(WebDriver webDriver) {

    }

    @Override
    public void afterNavigateForward(WebDriver webDriver) {

    }

    @Override
    public void beforeNavigateRefresh(WebDriver webDriver) {

    }

    @Override
    public void afterNavigateRefresh(WebDriver webDriver) {

    }

    @Override
    public void beforeFindBy(By by, WebElement webElement, WebDriver webDriver) {

    }

    @Override
    public void afterFindBy(By by, WebElement webElement, WebDriver webDriver) {

    }
}
