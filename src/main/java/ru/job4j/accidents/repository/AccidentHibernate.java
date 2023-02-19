package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AccidentHibernate implements AccidentRepository {
    private static final String FIND_ALL = "FROM Accident a JOIN FETCH a.type JOIN FETCH a.rule  ORDER BY id";
    private static final String FIND_BY_ID = "FROM Accident a JOIN FETCH a.type JOIN FETCH a.rule  WHERE id = :fId";

    private final CrudRepository crudRepository;

    @Override
    public Accident add(Accident accident) {
        crudRepository.run(session -> session.persist(accident));
        return accident;
    }

    @Override
    public List<Accident> findAll() {
        return crudRepository.query(FIND_ALL, Accident.class);
    }

    @Override
    public void update(Accident accident) {
        crudRepository.run(session -> session.update(accident));
    }

    @Override
    public Optional<Accident> findById(int id) {
        return crudRepository.optional(
                FIND_BY_ID, Accident.class,
                Map.of("fId", id)
        );
    }
}
