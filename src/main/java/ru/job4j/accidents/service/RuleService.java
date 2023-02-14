package ru.job4j.accidents.service;

import ru.job4j.accidents.model.Rule;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RuleService {

    void add(Rule rule);

    List<Rule> findAll();

    Optional<Rule> findById(int id);

    Set<Rule> findByIds(List<Integer> rIds);
}
