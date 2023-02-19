package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AccidentTypeMem implements AccidentTypeRepository {

    private final List<AccidentType> types = new ArrayList<>();

    public AccidentTypeMem() {
        types.add(new AccidentType(0, "Две машины"));
        types.add(new AccidentType(1, "Машина и человек"));
        types.add(new AccidentType(2, "Машина и велосипед"));
    }

    @Override
    public AccidentType add(AccidentType accidentType) {
        types.add(accidentType);
        return accidentType;
    }

    @Override
    public List<AccidentType> findAll() {
        return types;
    }

    @Override
    public Optional<AccidentType> findById(int id) {
        return Optional.ofNullable(types.get(id));
    }

    @Override
    public void update(AccidentType accidentType) {
        types.set(accidentType.getId(), accidentType);
    }
}
