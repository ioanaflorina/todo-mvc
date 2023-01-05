package com.todo.mvc.helpers;

import com.todo.mvc.enums.BrowserType;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

import java.time.Duration;

public class WebDriverManager {
    private static final Logger LOGGER = LogManager.getLogger(WebDriverManager.class);
    private static MutableCapabilities driverOptions;

    public static void addDriverOptions(MutableCapabilities driverOptions) {
        WebDriverManager.driverOptions = driverOptions;
    }

    public static WebDriver getWebDriver(BrowserType browserType) {
        WebDriver driver = null;
        switch (browserType) {
            case FIREFOX:
                if (driverOptions != null && driverOptions instanceof FirefoxOptions) {
                    driver = new FirefoxDriver((FirefoxOptions) driverOptions);
                } else {
                    driver = new FirefoxDriver();
                }
                break;
            case CHROME:
                if (driverOptions != null && driverOptions instanceof ChromeOptions) {
                    driver = new ChromeDriver((ChromeOptions) driverOptions);
                } else {
                    driver = new ChromeDriver();
                }
                break;
            case IE:
                if (driverOptions != null && driverOptions instanceof InternetExplorerOptions) {
                    driver = new InternetExplorerDriver((InternetExplorerOptions) driverOptions);
                } else {
                    driver = new InternetExplorerDriver();
                }
                break;
            default:
                LOGGER.debug("No valid browser, please check config!");
        }
        try {
            driver.manage().window().maximize();
        } catch (WebDriverException e) {
            LOGGER.debug("Driver maximize method threw exception on: " + e.getSystemInformation());
            LOGGER.debug("Resize browser using driver.manage().window().setSize(new Dimension(1280, 1024)).");
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(45));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(90));

        return driver;
    }

}
