package com.schoolwork.sprint.service;

import com.schoolwork.sprint.model.Role;
import com.schoolwork.sprint.model.Todo;
import com.schoolwork.sprint.model.User;
import com.schoolwork.sprint.repo.RoleRepo;
import com.schoolwork.sprint.repo.TodoRepo;
import com.schoolwork.sprint.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service(value = "service")
public class ServiceImpl implements com.schoolwork.sprint.service.Service {
    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private TodoRepo todoRepo;

    @Autowired
    private UserRepo userRepo;

    @Transactional
    @Override
    public Role saveRole(Role role) {
        Role newRole = new Role(role.getRolename());

        return roleRepo.save(newRole);
    }

    @Transactional
    @Override
    public User saveUser(User user) {
        User newUser = new User(user.getUsername(), user.getEmail(), user.getPassword());

        for (Todo t: user.getTodos()){
            Todo newTodo = todoRepo.getTodoByTodoid(t.getTodoid());
            newUser.addTodo(newTodo);
        }

        for (Role r: user.getRoles()){
            Role newRole = roleRepo.getRoleByRoleid(r.getRoleid());
            newUser.addRole(newRole);
        }
        return userRepo.save(newUser);
    }

    @Transactional
    @Override
    public Todo saveTodo(Todo todo, long userid) {
        Todo newTodo = new Todo(todo.getDescription(), todo.getDatetime(), getUserByUserid(userid));
        return todoRepo.save(newTodo);
    }

    @Transactional
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

    @Transactional
    @Override
    public void deleteUserById(long userid) {
        if (getUserByUserid(userid) != null){
            userRepo.deleteUserByUserid(userid);
        }
    }
}
