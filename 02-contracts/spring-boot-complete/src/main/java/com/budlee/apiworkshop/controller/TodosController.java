package com.budlee.apiworkshop.controller;

import com.budlee.apiworkshop.model.Todo;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class TodosController {

    AtomicInteger incrementKey = new AtomicInteger(1);
    Map<Integer, Todo> todosMap = new HashMap<>(20);

    @GetMapping("/todos")
    public Map<Integer, Todo> getAllTodos() {
        return todosMap;
    }

    @PostMapping(value = "/todos")
    public ResponseEntity<Void> createNewTodo(@RequestBody Todo todo) {
        final var key = incrementKey.getAndIncrement();
        todosMap.put(key, todo);
        final var uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(key).toUri();
        return ResponseEntity
                .created(uri)
                .build();
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<Todo> getTodo(@PathVariable(value = "id") Integer id) {
        return todosMap.containsKey(id) ?
                ResponseEntity.ok(todosMap.get(id)) :
                ResponseEntity.notFound().build();
    }


}