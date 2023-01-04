package com.todo.mvc.positive;

import com.todo.mvc.BaseAbstract;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;

public class DeleteTodoTests extends BaseAbstract {

    private String todoValue;

    @BeforeMethod
    public void addTodoItem() {
        todoValue = RandomStringUtils.randomAlphabetic(10);
        todosPage.fillNewTodoField(todoValue)
                .pressEnterKeyOnNewTodoField();
    }

    @Test(description = "This test verifies that a todo item can be deleted by selecting it and " +
            "clicking the Clear completed button.")
    public void clearCompletedTodoTest() {
        todosPage.selectTodoItem(todoValue)
                .clickClearCompletedBtn();
        assertThat("The deleted todo value appears in todos list.", todosPage.getAllTodoItems(),
                not(hasItem(todoValue)));
    }

    @Test(description = "This test verifies that a todo item can be deleted by clicking the destroy(X) button.")
    public void destroyTodoTest() {
        todosPage.destroyTodoItem(todoValue);
        assertThat("The deleted todo value appears in todos list.", todosPage.getAllTodoItems(),
                not(hasItem(todoValue)));
    }
}
