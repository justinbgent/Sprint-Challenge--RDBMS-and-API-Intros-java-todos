package com.schoolwork.sprint.service;


import com.schoolwork.sprint.model.Role;
import com.schoolwork.sprint.model.Todo;
import com.schoolwork.sprint.model.User;

public interface Service {
    Role saveRole(Role role);

    User saveUser(User user);

    Todo saveTodo(Todo todo, long userid);
}
