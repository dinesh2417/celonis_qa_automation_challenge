package com.trailcloud.core.driver;

import com.trailcloud.core.basetest.BaseTest;
import com.trailcloud.core.config.ConfigPropertyLoader;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

@Slf4j
public class DriverFactory{

    public static String browser;
    private ThreadLocal<WebDriver> tldriver = new ThreadLocal<>();
    private ConfigPropertyLoader prop = ConfigPropertyLoader.getInstance();;
    private ChromeOptions chromeOptions = new ChromeOptions();
    private FirefoxOptions firefoxOptions = new FirefoxOptions();

    public DriverFactory(@NonNull String browser) {
        this.browser = browser;
    }

    /**
     * getBrowser fetches browser type from maven options and property file.
     * Highest priority is given to maven options . if its null , it searches for property file
     * if property is also null or empty . it selects default as chrome browser
     */
    public synchronized WebDriver getDriver() {
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
            //chromeOptions.setExperimentalOption("useAutomationExtension", false);
            log.info("Launching Chrome browser");
            tldriver.set(new ChromeDriver(chromeOptions));
            log.info("Chrome browser launched");
            return tldriver.get();
        }
        if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.getInstance(DriverManagerType.FIREFOX).setup();
            File pathBinary = new File(System.getProperty("user.home") + prop.getPropertyValue("selenium-framework", "firefox.path"));
            FirefoxBinary firefoxBinary = new FirefoxBinary(pathBinary);
            DesiredCapabilities desired = DesiredCapabilities.firefox();
            desired.setCapability(FirefoxOptions.FIREFOX_OPTIONS, firefoxOptions.setBinary(firefoxBinary));
            log.info("Launching Firefox browser");
            tldriver.set(new FirefoxDriver(firefoxOptions));
            log.info("Firefox browser launched");
            return tldriver.get();
        }
        if (browser.equalsIgnoreCase("headless")) {
            WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
            chromeOptions.setExperimentalOption("useAutomationExtension", false);
            chromeOptions.setHeadless(true);
            tldriver.set(new ChromeDriver(chromeOptions));
            return tldriver.get();
        }
        return tldriver.get();
    }
}
