package com.schoolwork.sprint.repo;

import com.schoolwork.sprint.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepo extends CrudRepository<User, Long> {
    void deleteUserByUserid(long userid);

    List<User> getAllBy();

    User getUserByUserid(long userid);

    User save(User user);
}
