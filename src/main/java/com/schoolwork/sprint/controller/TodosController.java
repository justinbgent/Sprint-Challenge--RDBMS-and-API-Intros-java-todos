package com.schoolwork.sprint.controller;

import com.schoolwork.sprint.model.Todo;
import com.schoolwork.sprint.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/todos")
public class TodosController {
    @Autowired
    Service service;

    @PutMapping(value = "/todo/{todoid}", consumes = "application/json")
    ResponseEntity<?> updateTodo(@PathVariable long todoid,
                                 @RequestBody Todo todo){
        service.updateTodo(todo, todoid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
