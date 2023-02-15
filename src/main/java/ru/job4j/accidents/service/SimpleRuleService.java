package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.exception.InvalidParamsException;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RuleRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SimpleRuleService implements RuleService {

    private final RuleRepository ruleRepository;

    @Override
    public void add(Rule rule) {
        ruleRepository.add(rule);
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
        Set<Rule> rsl = ruleRepository.findAll().stream()
                .filter((r) -> rIds.contains(r.getId()))
                .collect(Collectors.toSet());
        if (rsl.size() != rIds.size()) {
            throw new InvalidParamsException();
        }
        return rsl;
    }
}
