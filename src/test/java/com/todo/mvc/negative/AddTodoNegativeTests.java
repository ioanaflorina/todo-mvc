package com.todo.mvc.negative;

import com.todo.mvc.BaseAbstract;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AddTodoNegativeTests extends BaseAbstract {

    @Test(description = "This test verifies that a todo item with empty value is not added in the list of todos.")
    public void doNotAddEmptyTodoTest() {
        int initialNoOfTodos = todosPage.getAllTodoItems().size();
        String todoValue = "";
        todosPage.fillNewTodoField(todoValue)
                .pressEnterKeyOnNewTodoField();
        assertThat("The number of todos after adding empty todo is different than the initial number of todos in the list.",
                todosPage.getAllTodoItems(), hasSize(equalTo(initialNoOfTodos)));
        assertThat("The todo with empty value is displayed on the todos list.", todosPage.getAllTodoItems(),
                not(hasItem(todoValue)));
    }
}
