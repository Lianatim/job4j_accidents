package ru.job4j.accidents.repository.jpa;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accidents.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryJpa extends CrudRepository<User, Integer> {
    List<User> findAll();
    Optional<User> findByUsername(String username);
}
