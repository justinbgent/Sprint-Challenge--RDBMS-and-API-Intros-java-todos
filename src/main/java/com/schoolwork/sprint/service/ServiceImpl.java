package com.schoolwork.sprint.service;

import com.schoolwork.sprint.model.Role;
import com.schoolwork.sprint.model.Todo;
import com.schoolwork.sprint.model.User;
import com.schoolwork.sprint.repo.RoleRepo;
import com.schoolwork.sprint.repo.TodoRepo;
import com.schoolwork.sprint.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "service")
public class ServiceImpl implements com.schoolwork.sprint.service.Service {
    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private TodoRepo todoRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public Role saveRole(Role role) {
        Role newRole = roleRepo.save(role);
        return newRole;
    }

    @Override
    public User saveUser(User user) {
        User newUser = userRepo.save(user);
        return newUser;
    }

    @Override
    public Todo saveTodo(Todo todo, long userid) {
        Todo newTodo = todoRepo.save(todo);
        newTodo.setUser(getUserByUserid(userid));
        return newTodo;
    }

    @Override
    public Todo updateTodo(Todo todo, long todoid) {
        Todo currentTodo = todoRepo.getTodoByTodoid(todoid);

        if (todo.getCompleted() != null){
            currentTodo.setCompleted(todo.getCompleted());
        }

        if (todo.getDatetime() != null){
            currentTodo.setDatetime(todo.getDatetime());
        }

        if (todo.getDescription() != null){
            currentTodo.setDescription(todo.getDescription());
        }

        return todoRepo.save(todo);
    }

    @Override
    public List<User> getUsers() {
        return userRepo.getAllBy();
    }

    @Override
    public User getUserByUserid(long userid) {
        return userRepo.getUserByUserid(userid);
    }

    @Override
    public void deleteUserById(long userid) {
        if (getUserByUserid(userid) != null){
            userRepo.deleteUserByUserid(userid);
        }
    }
}
