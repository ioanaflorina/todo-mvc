package com.todo.mvc.pages;

import com.todo.mvc.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.ArrayList;
import java.util.List;

public class TodosPage extends BasePage {

    @FindBy(xpath = "//input[contains(@class, 'new-todo')]")
    private WebElement newTodoField;
    @FindBys(@FindBy(xpath = "//ul[@class='todo-list']//li//div//label"))
    private List<WebElement> addedTodoItems;

    @FindBy(xpath = "//button[@class='clear-completed']")
    private WebElement clearCompletedBtn;

    @FindBy(xpath = "//button[@class='destroy']")
    private WebElement destroyBtn;

    public TodosPage(WebDriver driver) {
        super(driver);
    }

    public TodosPage fillNewTodoField(String value) {
        waitAndSendKeys(newTodoField, value);
        return this;
    }

    public TodosPage clickNewTodoField() {
        waitAndClick(newTodoField);
        return this;
    }

    public TodosPage pressEnterKeyOnNewTodoField() {
        newTodoField.sendKeys(Keys.ENTER);
        return this;
    }

    public List<String> getAllTodoItems() {
        List<String> addedTodos = new ArrayList<>();
        for (WebElement element : addedTodoItems) {
            addedTodos.add(getElementText(element));
        }

        return addedTodos;
    }

    public TodosPage editAddedTodo(String value) {
        Actions action = new Actions(driver);
        WebElement element = driver.findElement(By.xpath("//ul[@class='todo-list']//li//div" +
                "//label[text()='" + value + "']"));
        action.doubleClick(element).perform();

        return this;
    }

    public TodosPage changeValueOfAddedTodo(String oldValue, String newValue) {
        emptyTodoField(oldValue);
        driver.findElement(By.xpath("//ul[@class='todo-list']//li//div//label[text()='"
                + oldValue + "']/../following-sibling::input")).sendKeys(newValue);

        return this;
    }

    public TodosPage emptyTodoField(String todoValue) {
        Actions actions = new Actions(driver);
        actions.click(driver.findElement(By.xpath("//ul[@class='todo-list']//li//div//label[text()='"
                        + todoValue + "']/../following-sibling::input")))
                .sendKeys(Keys.END)
                .keyDown(Keys.SHIFT)
                .sendKeys(Keys.HOME)
                .keyUp(Keys.SHIFT)
                .sendKeys(Keys.BACK_SPACE)
                .perform();

        return this;
    }

    public TodosPage pressEnterKeyOnTodoField(String todoValue) {
        driver.findElement(By.xpath("//ul[@class='todo-list']//li//div//label[text()='"
                + todoValue + "']/../following-sibling::input")).sendKeys(Keys.ENTER);

        return this;
    }

    public TodosPage selectTodoItem(String todoValue) {
        driver.findElement(By.xpath("//ul[@class='todo-list']//li//div//label[text()='"
                + todoValue + "']/..//input[@class='toggle']")).click();
        return this;
    }

    public TodosPage clickClearCompletedBtn() {
        waitAndClick(clearCompletedBtn);
        return this;
    }

    public TodosPage destroyTodoItem(String todoValue) {
        Actions actions = new Actions(driver);
        WebElement element = driver.findElement(By.xpath("//ul[@class='todo-list']//li//div" +
                "//label[text()='" + todoValue + "']"));
        actions.moveToElement(element).perform();
        waitAndClick(destroyBtn);

        return this;
    }
}
