package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AccidentTypeHibernate implements AccidentTypeRepository {

    private static final String FIND_BY_ID = "FROM AccidentType WHERE id = :fId";
    private static final String FIND_ALL = "FROM AccidentType";

    private final CrudRepository crudRepository;

    @Override
    public AccidentType add(AccidentType accidentType) {
        crudRepository.run(session -> session.save(accidentType));
        return accidentType;
    }

    @Override
    public List<AccidentType> findAll() {
        return crudRepository.query(FIND_ALL, AccidentType.class);
    }

    @Override
    public Optional<AccidentType> findById(int id) {
        return crudRepository.optional(FIND_BY_ID,
                        AccidentType.class,
                        Map.of("fId", id));
    }

    @Override
    public void update(AccidentType accidentType) {
        crudRepository.run(session -> session.update(accidentType));
    }
}
