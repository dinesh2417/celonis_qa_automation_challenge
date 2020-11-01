package com.trailcloud.core.listener;

import com.trailcloud.core.basetest.BaseTest;
import com.trailcloud.core.util.TestUtil;
import io.qameta.allure.Attachment;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

/**
 * TestListener Class implements ITestListener which contains all the TestNG logs
 */
@Slf4j
public class TestListener extends BaseTest implements ITestListener {

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("Testcase : '" + result.getName() + "'is passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.error("Testng exception occured in" + result.getName() + "'");
        log.info("Testcase : '" + result.getName() + "'is failed");

        Object testClass = result.getInstance();
        // Allure ScreenShotRobot and SaveTestLog
        if (driver instanceof WebDriver) {
            try {
                // Save screenshot on project
                String screenshotPath = TestUtil.takeScreenshot();
                log.info("Screenshot taken and stored as : '" + screenshotPath + "'");
                // Save screenshot on allure.
                saveScreenshotPNG(driver);
                System.out.println("Screenshot captured for test case:" + getTestMethodName(result));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // Save a log on allure.
        saveTextLog(getTestMethodName(result) + " failed and screenshot taken!");

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.info("Testcase : '" + result.getName() + "'is skipped");
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        log.error("Testng exception occured in" + result.getName() + "'");
        log.error("Testcase : '" + result.getName() + "'is failed with timeout");
        try {
            String screenshotPath = TestUtil.takeScreenshot();
            log.info("Screenshot taken and stored as : '" + screenshotPath + "'");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStart(ITestContext context) {
        log.info("Test : '" + context.getName() + "'is started");
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("Test : '" + context.getName() + "'is finished");
    }

    @Override
    public void onTestStart(ITestResult result) {
        log.info("Testcase : '" + result.getName() + "'is started");
    }


    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    // Text attachments for Allure
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshotPNG(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    // Text attachments for Allure
    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

}