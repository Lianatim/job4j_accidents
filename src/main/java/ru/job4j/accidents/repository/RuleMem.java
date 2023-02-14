package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RuleMem implements RuleRepository {
    private List<Rule> rules = new ArrayList<>();

    public RuleMem() {
        rules.add(new Rule(0, "Статья. 1"));
        rules.add(new Rule(1, "Статья. 2"));
        rules.add(new Rule(2, "Статья. 3"));
    }

    @Override
    public void add(Rule rule) {
        rules.add(rule);
    }

    @Override
    public List<Rule> findAll() {
        return rules;
    }

    @Override
    public Optional<Rule> findById(int id) {
        return Optional.ofNullable(rules.get(id));
    }

}