package com.schoolwork.sprint.controller;

import com.schoolwork.sprint.model.Todo;
import com.schoolwork.sprint.model.User;
import com.schoolwork.sprint.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UsersController {
    @Autowired
    private Service service;

    @GetMapping(value = "/users", produces = "application/json")
    ResponseEntity<?> getAllUsers(){
        List<User> users = service.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/user/{userid}", produces = "application/json")
    ResponseEntity<?> getUserById(@PathVariable long userid){
        User user = service.getUserByUserid(userid);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(value = "/user", consumes = "application/json")
    ResponseEntity<?> addUser(@RequestBody User user){
        User newUser = service.saveUser(user);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{userid}")
                .buildAndExpand(newUser.getUserid())
                .toUri();
        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @PostMapping(value = "/todo/{userid}", consumes = "application/json")
    ResponseEntity<?> addTodo(@PathVariable long userid, @RequestBody Todo todo){
        service.saveTodo(todo, userid);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI userURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{userid}")
                .buildAndExpand(userid)
                .toUri();
        responseHeaders.setLocation(userURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/userid/{userid}")
    ResponseEntity<?> deleteUser(@PathVariable long userid){
        service.deleteUserById(userid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
