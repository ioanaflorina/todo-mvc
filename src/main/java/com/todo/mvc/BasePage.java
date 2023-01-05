package com.todo.mvc;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.annotation.Nullable;
import java.time.Duration;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.todo.mvc.data.Constants.APP_SLEEP_TIME;

public abstract class BasePage {
    protected WebDriver driver;
    protected static final Logger LOGGER = LogManager.getLogger(BasePage.class);

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void waitAndSendKeys(WebElement element, String text) {
        waitForElementVisible(element);
        element.clear();
        element.sendKeys(text);
    }

    public WebElement waitForElementVisible(WebElement element) {
        Duration duration = Duration.ofSeconds(APP_SLEEP_TIME);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        int att = 0;
        while (att < 2) {
            try {
                wait.until(ExpectedConditions.visibilityOf(element));
                break;
            } catch (StaleElementReferenceException e) {
                LOGGER.debug("Stale element exception caught on element " + element + ".");
            }
            att++;
        }
        return element;
    }

    protected String getElementText(final WebElement element) {
        try {
            waitForElementVisible(element);
            return element.getText();
        } catch (NoSuchElementException | TimeoutException e) {
            printElementNotFound(e);
            throw e;
        }
    }

    public static void printElementNotFound(Exception e) {
        Pattern pattern = Pattern.compile("('.+')");
        Matcher matcher = pattern.matcher(e.getMessage());
        if (matcher.find()) {
            LOGGER.error("Element located by: " + matcher.group(0) + " was not found.");
        } else {
            LOGGER.error("Element was not found. Matcher failed. Please investigate.");
        }
    }

    public void waitAndClick(WebElement element) {
        waitForElementVisible(element);
        element.click();
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

}