package com.todo.mvc.positive;

import com.todo.mvc.BaseAbstract;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;

public class EditTodoTests extends BaseAbstract {

    private String todoValue;

    @BeforeMethod
    public void addTodoItem() {
        todoValue = RandomStringUtils.randomAlphabetic(10);
        todosPage.fillNewTodoField(todoValue)
                .pressEnterKeyOnNewTodoField();
    }


    @Test(description = "This test verifies that a todo item can be edited and the new value is displayed on the todos " +
            "listing page.")
    public void editTodoTest() {
        String newTodoValue = RandomStringUtils.randomAlphabetic(5);
        todosPage.editAddedTodo(todoValue)
                .changeValueOfAddedTodo(todoValue, newTodoValue)
                .pressEnterKeyOnTodoField(todoValue);
        assertThat("The new todo value does not appear in todos list.", todosPage.getAllTodoItems(),
                hasItem(newTodoValue));
        assertThat("The old todo value still appears in todos list.", todosPage.getAllTodoItems(),
                not(hasItem(todoValue)));
    }
}
