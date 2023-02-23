package ru.job4j.accidents.service.jpa;

import org.springframework.stereotype.Service;
import ru.job4j.accidents.exception.InvalidParamsException;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.jpa.RuleRepositoryJpa;
import ru.job4j.accidents.service.RuleService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RuleServiceJpa implements RuleService {
    private final RuleRepositoryJpa ruleRepository;

    public RuleServiceJpa(RuleRepositoryJpa ruleRepository) {
        this.ruleRepository = ruleRepository;
    }


    @Override
    public void add(Rule rule) {
        ruleRepository.save(rule);
    }

    @Override
    public List<Rule> findAll() {
        return ruleRepository.findAll();
    }

    @Override
    public Optional<Rule> findById(int id) {
        return ruleRepository.findById(id);
    }

    @Override
    public Set<Rule> findByIds(List<Integer> rIds) {
        Set<Rule> rsl = findAll().stream()
                .filter((r) -> rIds.contains(r.getId()))
                .collect(Collectors.toSet());
        if (rsl.size() != rIds.size()) {
            throw new InvalidParamsException();
        }
        return rsl;
    }
}
