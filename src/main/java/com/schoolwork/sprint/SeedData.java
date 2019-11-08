package com.schoolwork.sprint;

import com.schoolwork.sprint.model.Role;
import com.schoolwork.sprint.model.Todo;
import com.schoolwork.sprint.model.User;
import com.schoolwork.sprint.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Transactional
@Component
public class SeedData implements CommandLineRunner
{
    @Autowired
    Service service;


    @Override
    public void run(String[] args) throws Exception
    {
        Role r1 = new Role("admin");
        Role r2 = new Role("user");
        Role r3 = new Role("data");

        r1 = service.saveRole(r1);
        r2 = service.saveRole(r2);
        r3 = service.saveRole(r3);

        com.schoolwork.sprint.model.User u1 = new User("admin",
                           "password",
                           "admin@lambdaschool.local");
        u1.addRole(r1);
        u1.addRole(r2);
        u1.addRole(r3);
        u1.getTodos()
          .add(new Todo("Finish java-orders-swagger",
                        new Date(),
                        u1));
        u1.getTodos()
          .add(new Todo("Feed the turtles",
                        new Date(),
                        u1));
        u1.getTodos()
          .add(new Todo("Complete the sprint challenge",
                        new Date(),
                        u1));

        service.saveUser(u1);

        User u2 = new User("cinnamon",
                           "1234567",
                           "cinnamon@lambdaschool.local");
        u2.addRole(r2);
        u2.addRole(r3);
        u2.getTodos()
          .add(new Todo("Walk the dogs",
                        new Date(),
                        u2));
        u2.getTodos()
          .add(new Todo("provide feedback to my instructor",
                        new Date(),
                        u2));
        service.saveUser(u2);

        // user
        User u3 = new User("barnbarn",
                           "ILuvM4th!",
                           "barnbarn@lambdaschool.local");
        u3.addRole(r2);
        service.saveUser(u3);


        User u4 = new User("puttat",
                           "password",
                           "puttat@school.lambda");
        u4.addRole(r2);
        service.saveUser(u4);

        User u5 = new User("misskitty",
                           "password",
                           "misskitty@school.lambda");
        service.saveUser(u5);
    }
}
