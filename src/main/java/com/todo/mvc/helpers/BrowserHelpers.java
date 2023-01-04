package com.todo.mvc.helpers;

import com.todo.mvc.enums.BrowserType;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.util.HashMap;

import static com.todo.mvc.data.Constants.DOWNLOADS_DIRECTORY;
import static com.todo.mvc.enums.BrowserType.CHROME;
import static com.todo.mvc.enums.BrowserType.FIREFOX;

public class BrowserHelpers {
    protected static final Logger LOGGER = LogManager.getLogger(BrowserHelpers.class);
    private static ChromeOptions setChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--window-size=1920,1080", "disable-infobars", "--enable-automation",
                /*"--headless",*/ "--no-sandbox");
        return chromeOptions;
    }

    private static HashMap<String, Object> setChromePrefs(ChromeOptions chromeOptions, String downloadsDirectory) {
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", downloadsDirectory);
        chromePrefs.put("download.prompt_for_download", false);
        chromePrefs.put("plugins.always_open_pdf_externally", false);
        chromePrefs.put("safebrowsing.enabled", true);

        chromeOptions.setExperimentalOption("prefs", chromeOptions);
        return chromePrefs;
    }

    private static FirefoxOptions setFirefoxOptions() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--window-size=1920,1080", "disable-infobars", "--enable-automation", "--headless", "--no-sandbox");
        firefoxOptions.addPreference("dom.disable_beforeunload", true);
        return firefoxOptions;
    }

    private static FirefoxProfile setFirefoxPrefs(FirefoxOptions firefoxOptions) {
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        // set downloads directory
        firefoxProfile.setPreference("browser.download.folderList", 2);
        firefoxProfile.setPreference("browser.download.dir", DOWNLOADS_DIRECTORY);
        // do not show open or save file window for the mentioned file types
        firefoxProfile.setPreference("browser.helperApps.neverAsk.openFile", "image/jpeg, application/octect-stream, " +
                "application/pdf, application/x-gzip");
        firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "image/jpeg, application/octect-stream, " +
                "application/pdf, application/x-gzip");
        firefoxProfile.setPreference("pdfjs.disabled", true);
        firefoxProfile.setPreference("dom.disable_beforeunload", false);

        firefoxOptions.setProfile(firefoxProfile);

        return firefoxProfile;
    }

    public static BrowserType chooseBrowser(String downloadsDirectory) {
        BrowserType browserType;
        switch (System.getProperty("browser")) {
            case "chrome":
                browserType = CHROME;
                ChromeOptions options = setChromeOptions();
                options.setExperimentalOption("prefs", setChromePrefs(options, downloadsDirectory));
                WebDriverManager.addDriverOptions(options);
                System.setProperty("webdriver.chrome.silentOutput", "true");
                break;
            case "firefox":
                browserType = FIREFOX;
                FirefoxOptions firefoxOptions = setFirefoxOptions();
                /*firefoxOptions.setCapability(FirefoxDriver.PROFILE, setFirefoxPrefs(firefoxOptions));
                WebDriverManager.addDriverOptions(firefoxOptions);
                // disable firefox logging
                System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
                System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");*/
                break;
            default:
                browserType = CHROME;
                options = setChromeOptions();
                options.setExperimentalOption("prefs", setChromePrefs(options, downloadsDirectory));
                WebDriverManager.addDriverOptions(options);
                LOGGER.info("The browser type does not exist! Chrome was chosen by default");
        }
        return browserType;
    }
}
