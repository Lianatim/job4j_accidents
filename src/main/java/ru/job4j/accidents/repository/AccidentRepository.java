package ru.job4j.accidents.repository;

import ru.job4j.accidents.model.Accident;

import java.util.List;
import java.util.Optional;

public interface AccidentRepository {
    Accident add(Accident accident);

    List<Accident> findAll();

    void update(Accident accident);

    Optional<Accident> findById(int id);
}
