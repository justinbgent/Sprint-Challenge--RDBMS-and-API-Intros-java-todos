package com.schoolwork.sprint.service;

import com.schoolwork.sprint.model.Role;
import com.schoolwork.sprint.model.Todo;
import com.schoolwork.sprint.model.User;
import org.springframework.stereotype.Service;

@Service(value = "service")
public class ServiceImpl implements com.schoolwork.sprint.service.Service {
    @Override
    public Role saveRole(Role role) {
        return null;
    }

    @Override
    public User saveUser(User user) {
        return null;
    }

    @Override
    public Todo saveTodo(Todo todo, long userid) {
        return null;
    }
}
