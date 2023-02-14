package ru.job4j.accidents.repository;

import ru.job4j.accidents.model.Rule;

import java.util.List;
import java.util.Optional;

public interface RuleRepository {

    void add(Rule rule);

    List<Rule> findAll();

    Optional<Rule> findById(int id);
}
