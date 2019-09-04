package com.budlee.apiworkshop;

import com.budlee.apiworkshop.controller.TodosController;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;

public abstract class TodoBaseRest {
    TodosController todoController = new TodosController();

    @Before
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(this.todoController);
    }

}
