package ru.job4j.accidents.repository.jpa;

import ru.job4j.accidents.model.Authority;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorityRepositoryJpa extends CrudRepository<Authority, Integer> {

    Authority findByAuthority(String authority);

    List<Authority> findAll();
}
