package com.todo.mvc.positive;

import com.todo.mvc.BaseAbstract;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

public class AddTodoTests extends BaseAbstract {

    @Test(description = "This test verifies that a todo item is added in the list of todos.")
    public void addTodoTest() {
        String todoValue = RandomStringUtils.randomAlphabetic(10);
        todosPage.fillNewTodoField(todoValue)
                .pressEnterKeyOnNewTodoField();
        assertThat("The new added todo is not displayed on the todos list.", todosPage.getAllTodoItems(),
                hasItem(todoValue));
    }
}
