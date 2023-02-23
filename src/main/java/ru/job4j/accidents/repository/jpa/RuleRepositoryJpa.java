package ru.job4j.accidents.repository.jpa;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accidents.model.Rule;

import java.util.List;

public interface RuleRepositoryJpa extends CrudRepository<Rule, Integer> {
    List<Rule> findAll();
}
