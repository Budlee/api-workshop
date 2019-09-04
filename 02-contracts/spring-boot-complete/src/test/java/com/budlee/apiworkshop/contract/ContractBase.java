package com.budlee.apiworkshop.contract;

import com.budlee.apiworkshop.controller.TodosController;
import com.budlee.apiworkshop.controller.WorkshopController;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;

public class ContractBase {
    @Before
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(new TodosController());
    }
}
