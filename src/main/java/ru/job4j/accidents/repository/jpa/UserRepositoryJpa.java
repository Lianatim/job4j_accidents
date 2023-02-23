package ru.job4j.accidents.repository.jpa;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accidents.model.User;

public interface UserRepositoryJpa extends CrudRepository<User, Integer> {
}
