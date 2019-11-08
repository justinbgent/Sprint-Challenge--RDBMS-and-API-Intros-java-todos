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

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UsersController {
    @Autowired
    private Service service;

    // http://localhost:2019/users/users
    @GetMapping(value = "/users", produces = "application/json")
    ResponseEntity<?> getAllUsers(){
        List<User> users = service.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // http://localhost:2019/users/user/{userid}
    @GetMapping(value = "/user/{userid}", produces = "application/json")
    ResponseEntity<?> getUserById(@PathVariable long userid){
        User user = service.getUserByUserid(userid);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // http://localhost:2019/users/user
    @PostMapping(value = "/user", consumes = "application/json")
    ResponseEntity<?> addUser(@Valid @RequestBody User user){
        User newUser = service.saveUser(user);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{userid}")
                .buildAndExpand(newUser.getUserid())
                .toUri();
        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    // http://localhost:2019/users/todo/{userid}
    @PostMapping(value = "/todo/{userid}", consumes = "application/json")
    ResponseEntity<?> addTodo(@PathVariable long userid, @Valid @RequestBody Todo todo){
        service.saveTodo(todo, userid);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI userURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{userid}")
                .buildAndExpand(userid)
                .toUri();
        responseHeaders.setLocation(userURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    // http://localhost:2019/users/userid/{userid}
    @DeleteMapping(value = "/userid/{userid}")
    ResponseEntity<?> deleteUser(@PathVariable long userid){
        service.deleteUserById(userid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
