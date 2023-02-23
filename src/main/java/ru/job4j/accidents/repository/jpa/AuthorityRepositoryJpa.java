package ru.job4j.accidents.repository.jpa;

import ru.job4j.accidents.model.Authority;
import org.springframework.data.repository.CrudRepository;

public interface AuthorityRepositoryJpa extends CrudRepository<Authority, Integer> {

    Authority findByAuthority(String authority);
}
