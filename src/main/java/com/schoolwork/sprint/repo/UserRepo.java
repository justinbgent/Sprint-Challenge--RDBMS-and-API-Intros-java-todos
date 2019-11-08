package com.schoolwork.sprint.repo;

import com.schoolwork.sprint.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {
}
