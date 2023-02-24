package ru.job4j.accidents.service.jpa;

import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.User;
import ru.job4j.accidents.repository.jpa.UserRepositoryJpa;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceJpa {

    private final UserRepositoryJpa userRepositoryJpa;

    public UserServiceJpa(UserRepositoryJpa userRepositoryJpa) {
        this.userRepositoryJpa = userRepositoryJpa;
    }

    public void save(User user) {
        userRepositoryJpa.save(user);
    }

    public List<User> findAll() {
        return userRepositoryJpa.findAll();
    }

    public Optional<User> findById(int id) {
        return userRepositoryJpa.findById(id);
    }

}
