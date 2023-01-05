package com.todo.mvc.negative;

import com.todo.mvc.BaseAbstract;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

public class EditTodoNegativeTests extends BaseAbstract {
    private String todoValue;

    @BeforeMethod
    public void addTodoItem() {
        todoValue = RandomStringUtils.randomAlphabetic(10);
        todosPage.fillNewTodoField(todoValue)
                .pressEnterKeyOnNewTodoField();
    }

    @Test(description = "This test verifies that edited value for todo is displayed on the listing page " +
            "after refreshing the page.")
    public void displayEditedTodoAfterPageRefreshTest() {
        String newTodoValue = RandomStringUtils.randomAlphabetic(5);
        todosPage.editAddedTodo(todoValue)
                .changeValueOfAddedTodo(todoValue, newTodoValue)
                .pressEnterKeyOnTodoField(todoValue)
                .refreshPage();
        assertThat("The new todo value does not appear in todos list.", todosPage.getAllTodoItems(),
                hasItem(newTodoValue));
        assertThat("The old todo value still appears in todos list.", todosPage.getAllTodoItems(),
                not(hasItem(todoValue)));
    }

    @Test(description = "This test verifies that it is not possible to save an empty todo on the listing page.")
    public void doNotDisplayEmptyTodoItemOnListTest() {
        todosPage.editAddedTodo(todoValue)
                .emptyTodoField(todoValue)
                .clickNewTodoField();
        assertThat("The todo value does not appear in todos list.", todosPage.getAllTodoItems(),
                hasItem(todoValue));
    }

    @Test(description = "This test verifies that the old todo is still displayed in the list if the user tried" +
            " to update it with an empty value.")
    public void doNotUpdateTheAddedTodoWithEmptyValueTest() {
        int initialNoOfTodos = todosPage.getAllTodoItems().size();
        todosPage.editAddedTodo(todoValue)
                .emptyTodoField(todoValue)
                .pressEnterKeyOnTodoField(todoValue);
        assertThat("The number of todos after trying to update todo with empty value is different than the " +
                        "initial number of todos in the list.",
                todosPage.getAllTodoItems(), hasSize(equalTo(initialNoOfTodos)));
        assertThat("The value of the initial todo was changed.", todosPage.getAllTodoItems(), hasItem(todoValue));
    }
}
