package com.todo.mvc;

import com.todo.mvc.conf.ProjectConfiguration;
import com.todo.mvc.enums.BrowserType;
import com.todo.mvc.helpers.WebDriverManager;
import com.todo.mvc.pages.TodosPage;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static com.todo.mvc.data.Constants.DOWNLOADS_DIRECTORY;
import static com.todo.mvc.helpers.BrowserHelpers.chooseBrowser;

@ContextConfiguration(classes = ProjectConfiguration.class)
public abstract class BaseAbstract extends AbstractTestNGSpringContextTests {

    @Value("${app.url}")
    public String appUrl;
    protected WebDriver driver;
    protected static final BrowserType BROWSER = chooseBrowser(DOWNLOADS_DIRECTORY);

    protected TodosPage todosPage;

    @BeforeClass
    public void setupDriver() {
        driver = WebDriverManager.getWebDriver(BROWSER);
        driver.get(appUrl);

        todosPage = new TodosPage(driver);
    }

    @AfterClass(alwaysRun = true)
    public void closeBrowser() {
        driver.quit();
    }
}
