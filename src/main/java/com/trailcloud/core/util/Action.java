package com.trailcloud.core.util;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

@Slf4j
public class Action {

    private WebDriver driver;
    private static Actions actions;
    private static JavascriptExecutor js;
    private static Select select;

    public Action(WebDriver driver) {
        actions = new Actions(driver);
        js = (JavascriptExecutor) driver;
        this.driver = driver;
    }

    public void click(WebElement element) throws Exception {
        try {
            Wait.elementToBeClickable(driver, element).click();
            log.info(element + " is succesfully clicked");
        } catch (Exception e) {
            log.error("Cannot click " + element);
            e.printStackTrace();
            throw new Exception("Cannot click " + element);
        }
    }

    public void doubleClick(WebElement element) throws Exception {
        try {
            actions.doubleClick(Wait.elementToBeClickable(driver, element)).build().perform();
            log.info(element + " is succesfully clicked");
        } catch (Exception e) {
            log.error("Cannot doubleClick " + element);
            e.printStackTrace();
            throw new Exception("Cannot doubleClick " + element);
        }

    }

    public void rightClick(WebElement element) throws Exception {
        try {
            actions.moveToElement(Wait.elementToBeClickable(driver, element)).contextClick().build().perform();
            log.info(element + " is succesfully rightClicked");
        } catch (Exception e) {
            log.error("Cannot rightClick " + element);
            throw new Exception("Cannot rightClick " + element);
        }
    }

    public void enterValue(WebElement element, String value) throws Exception {
        try {
            Wait.elementToBeClickable(driver, element).clear();
            element.sendKeys(value);
            //js.executeScript("arguments[0].value='" + value + "';", element);
            log.info("Entered value: " + value + " on " + element);
        } catch (Exception e) {
            log.error("Cannot enter value in : " + value + " on " + element);
            throw new Exception("Cannot enter value in : " + element);
        }
    }

    public void selectByValue(WebElement element, String value) throws Exception {
        try {
            select = new Select(element);
            select.selectByValue(value);
            log.info("Selected " + element + " with " + value);
        } catch (Exception e) {
            log.error("Cannot select: " + element + " with " + value);
            throw new Exception("Cannot select " + element);
        }
    }

    public void selectByVisibleText(WebElement element, String text) throws Exception {
        try {
            select = new Select(element);
            select.selectByVisibleText(text);
            log.info("Selected " + element + " with " + text);
        } catch (Exception e) {
            log.error("Cannot select: " + element + " with " + text);
            throw new Exception("Cannot select " + element);
        }
    }

    public void selectByIndex(WebElement element, int index) throws Exception {
        try {
            select = new Select(Wait.elementToBeClickable(driver, element));
            select.selectByIndex(index);
            log.info("Selected " + element + " with " + index);
        } catch (Exception e) {
            log.error("Cannot select: " + element + " with " + index);
            throw new Exception("Cannot select " + element);
        }
    }

    public String getText(WebElement element) throws Exception {
        try {
            String text = Wait.elementToBeClickable(driver, element).getText();
            log.info(element + " found with gettext String: " + text);
            return text;
        } catch (Exception e) {
            log.error(element + " not found with gettext String");
            return null;
        }
    }

    public void moveToElement(WebElement element) throws Exception {
        try {
            actions.moveToElement(Wait.elementToBeClickable(driver, element)).build().perform();
            log.info("Cursor moved to element: " + element);
        } catch (Exception e) {
            log.error("Cursor not moved to element: " + element);
            throw new Exception("Cannot move cursor " + element);
        }
    }

    public void moveToElementAndClick(WebElement element) throws Exception {
        try {
            actions.moveToElement(Wait.elementToBeClickable(driver, element)).click().build().perform();
            log.info("Cursor moved to element: " + element + " and clicked");
        } catch (Exception e) {
            log.error("Cursor not moved to element: " + element);
            throw new Exception("Cannot move cursor " + element);
        }
    }

    public void windowScrollBy(int xPixels, int yPixels) throws Exception {
        try {
            js.executeScript("window.scrollBy(arguments[0],arguments[1]);", xPixels, yPixels);
            log.info("Windows scrolled by " + xPixels + "," + yPixels);
        } catch (Exception e) {
            log.error("Windows not scrolled");
            throw new Exception("Cannot scroll window ");
        }

    }

    public void windowScrollToView(WebElement element) throws Exception {
        try {
            js.executeScript("arguments[0].scrollIntoView();", element);
            log.info("Windows scrolled until to view element: " + element);
        } catch (Exception e) {
            log.error("Windows not scrolled");
            throw new Exception("Cannot scroll window");
        }
    }
}

