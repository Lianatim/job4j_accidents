package ru.job4j.accidents.repository.jpa;

import ru.job4j.accidents.model.Rule;
import org.springframework.data.repository.CrudRepository;

public interface RuleRepositoryJpa extends CrudRepository<Rule, Integer> {
}
