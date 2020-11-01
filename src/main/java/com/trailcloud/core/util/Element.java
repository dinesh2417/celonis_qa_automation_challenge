package com.trailcloud.core.util;


import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Class used to implement Wait for an Element , till it is not found beyond certain time
 */
@Slf4j
public class Element {
    static WebElement element;
    static List<WebElement> elements;

    /**
     * Method implement to search for element for a certain time.
     * params timeout in seconds ( max waiting time ) if not found will throw an error
     * Returns WebElement
     */
    public static WebElement getElement(WebDriver driver, By locator, int timeout) throws Exception {
        for (int i = 0; i <= timeout; i++) {
            try {
                element = driver.findElement(locator);
                log.info("Found element with : " + locator);
                return element;
            } catch (Exception e) {
                log.info(i + ". Trying to find element with " + locator);
            }
            Thread.sleep(1000);
        }
        StackTraceElement stackTrace = new Throwable().getStackTrace()[1];
        log.error("Cannot find element with : " + locator);
        log.error("Cannot find element : " + stackTrace.getClassName() + "." + stackTrace.getMethodName());
        throw new Exception("Unable to find Element");
    }

    /**
     * Method implement to search for all elements for a certain time.
     * params timeout in seconds ( max waiting time ) if not found will throw an error
     * Returns List of WebElements
     */
    public static List<WebElement> getElements(WebDriver driver, By locator, int timeout) throws Exception {
        for (int i = 0; i <= timeout; i++) {
            try {
                if (driver.findElements(locator).size() == 0) throw new Exception();
                elements = driver.findElements(locator);
                log.info("Found total " + elements.size() + " elements with : " + locator);
                return elements;
            } catch (Exception e) {
                log.info(i + ". Trying to find all elements with " + locator);
            }
            Thread.sleep(1000);
        }
        StackTraceElement stackTrace = new Throwable().getStackTrace()[1];
        log.error("Cannot find all elements with : " + locator);
        log.error("Cannot find all elements : " + stackTrace.getClassName() + "." + stackTrace.getMethodName());
        throw new Exception("Unable to find all Elements");
    }

    public static Boolean isElementAvailable(WebDriver driver, By locator, int timeout) throws InterruptedException {
        Boolean isElementAvailable = false;
        for (int i = 0; i <= timeout; i++) {
            try {
                element = driver.findElement(locator);
                log.info("Found element with : " + locator);
                isElementAvailable = true;
            } catch (Exception e) {
                log.info(i + ". Trying to find element with " + locator);
            }
            Thread.sleep(500);
        }
        return isElementAvailable;
    }

    public static boolean isDisplayed(WebElement element) {
        if (element.isDisplayed())
            log.info(element + " is displayed");
        else
            log.info(element + " is not displayed");
        return element.isDisplayed();
    }

    public static boolean isSelected(WebElement element) {
        if (element.isSelected())
            log.info(element + " is selected");
        else
            log.info(element + " is not selected");
        return element.isSelected();
    }

    public static boolean isEnabled(WebElement element) {
        if (element.isEnabled())
            log.info(element + " is enabled");
        else
            log.info(element + " is not enabled");
        return element.isEnabled();
    }

    public static boolean isElementClickable(WebDriver driver, WebElement element) {
        try {
            Wait.elementToBeClickable(driver, element);
            return true;
        } catch (WebDriverException e) {
            return false;
        }
    }

    public static String getAttribute(WebDriver driver, WebElement element, String attribute) {
        return Wait.elementToBeClickable(driver, element).getAttribute(attribute);
    }

}

