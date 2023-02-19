package ru.job4j.accidents.repository;

import ru.job4j.accidents.model.Rule;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RuleRepository {

    Rule add(Rule rule);

    List<Rule> findAll();

    Optional<Rule> findById(int id);

    void update(Rule rule);

}
