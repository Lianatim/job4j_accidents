package ru.job4j.accidents.repository.hbm;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.CrudRepository;
import ru.job4j.accidents.repository.RuleRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class RuleHibernate implements RuleRepository {

    private static final String FIND_BY_ID = "FROM Rule WHERE id = :fId";
    private static final String FIND_ALL = "FROM Rule";
    private final CrudRepository crudRepository;

    @Override
    public Rule add(Rule rule) {
        crudRepository.run(session -> session.save(rule));
        return rule;
    }

    @Override
    public List<Rule> findAll() {
        return crudRepository.query(FIND_ALL, Rule.class);
    }

    @Override
    public Optional<Rule> findById(int id) {
        return crudRepository.optional(FIND_BY_ID,
                        Rule.class,
                        Map.of("fId", id));
    }

    @Override
    public void update(Rule rule) {
        crudRepository.run(session -> session.update(rule));
    }
}
