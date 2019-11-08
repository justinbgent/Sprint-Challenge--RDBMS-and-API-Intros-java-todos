package com.schoolwork.sprint.repo;

import com.schoolwork.sprint.model.Todo;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepo extends CrudRepository<Todo, Long> {
}
