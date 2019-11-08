package com.schoolwork.sprint.service;


import com.schoolwork.sprint.model.Role;
import com.schoolwork.sprint.model.Todo;
import com.schoolwork.sprint.model.User;

import java.util.List;

public interface Service {
    Role saveRole(Role role);

    User saveUser(User user);

    Todo saveTodo(Todo todo, long userid);

    Todo updateTodo(Todo todo, long todoid);

    List<User> getUsers();

    User getUserByUserid(long userid);

    void deleteUserById(long userid);

    //Todo getTodoById(long todoid);
}
