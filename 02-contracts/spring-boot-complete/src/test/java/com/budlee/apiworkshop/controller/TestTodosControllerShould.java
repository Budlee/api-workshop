package com.budlee.apiworkshop.controller;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import com.budlee.apiworkshop.model.Todo;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.*;

public class TestTodosControllerShould {

    @Rule
    public PactProviderRuleMk2 mockProvider =
            new PactProviderRuleMk2("todo-api-producer-pact", "localhost", 8083, this);


    @Pact(consumer = "todo-api-consumer-pact")
    public RequestResponsePact beerNotOk(PactDslWithProvider builder) {
        return builder.
                given("")
                .uponReceiving("A successful PUT operation to update a Todo")
                .matchPath("/todos/1")
                .method("PUT")
                .headers("Content-Type", "application/json")
                .body("{\"message\":\"Make all the beds\"}")
                .willRespondWith()
                .status(204)
                .toPact();
    }

    TodosController controller = new TodosController();

    @Before
    public void setUp(){
//        this.controller.createNewTodo(new Todo("Make the bed"));
    }


    @Test
    @PactVerification
    public void runUpdateTodo() {

//        this.controller.createNewTodo(new Todo("Make the bed"));

        //NO CONTENT
        assertEquals(this.controller.updateTodo(1, new Todo("Make all the beds")).getStatusCodeValue(), 204);
//        assertEquals(this.controller.getTodo(1).getBody(), new Todo("Make all the beds"));

    }
}