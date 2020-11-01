package com.trailcloud.core.util;


import com.trailcloud.core.basetest.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Wait Class contains all the web driver waits
 */
public class Wait extends BaseTest {
    static WebDriverWait wait;
    static WebElement elementToBeClickable;
    static List<WebElement> visibilityOfAllElements;

    public static WebElement explicitWait(WebDriver driver, WebElement element, int timeoutInSeconds, int sleepInMillis) {
        wait = new WebDriverWait(driver, timeoutInSeconds);
        elementToBeClickable = wait.until(ExpectedConditions.elementToBeClickable(element));
        return elementToBeClickable;
    }

    public static WebElement elementToBeClickable(WebDriver driver, WebElement element) {
        wait = new WebDriverWait(driver, Long.parseLong(prop.getPropertyValue("selenium-framework", "expilicitwait.timeoutInSeconds")));
        elementToBeClickable = wait.until(ExpectedConditions.elementToBeClickable(element));
        return elementToBeClickable;
    }

    public static WebElement elementToBeClickable(WebDriver driver, By locator) {
        wait = new WebDriverWait(driver, Long.parseLong(prop.getPropertyValue("selenium-framework", "expilicitwait.timeoutInSeconds")));
        elementToBeClickable = wait.until(ExpectedConditions.elementToBeClickable(locator));
        return elementToBeClickable;
    }

    public static WebElement presenceOfElementLocated(WebDriver driver, By locator) {
        wait = new WebDriverWait(driver, Long.parseLong(prop.getPropertyValue("selenium-framework", "expilicitwait.timeoutInSeconds")));
        elementToBeClickable = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return elementToBeClickable;
    }

    public static List<WebElement> visibilityOfAllElements(WebDriver driver, List<WebElement> elements) {
        wait = new WebDriverWait(driver, Long.parseLong(prop.getPropertyValue("selenium-framework", "expilicitwait.timeoutInSeconds")),
                Long.parseLong(prop.getPropertyValue("selenium-framework", "expilicitwait.sleepInMilliSeconds")));
        visibilityOfAllElements = wait.until(ExpectedConditions.visibilityOfAllElements(elements));
        return visibilityOfAllElements;
    }

    public static void implicitWait(WebDriver driver, int timeoutInSeconds) {
        driver.manage().timeouts().implicitlyWait(timeoutInSeconds, TimeUnit.SECONDS);
    }

    public static void pageLoadTimeoutWait(WebDriver driver, int timeoutInSeconds) {
        driver.manage().timeouts().pageLoadTimeout(timeoutInSeconds, TimeUnit.SECONDS);
    }
}