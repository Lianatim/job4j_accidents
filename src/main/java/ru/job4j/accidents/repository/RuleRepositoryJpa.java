package ru.job4j.accidents.repository;

import ru.job4j.accidents.model.Rule;
import org.springframework.data.repository.CrudRepository;

public interface RuleRepositoryJpa extends CrudRepository<Rule, Integer> {
}
