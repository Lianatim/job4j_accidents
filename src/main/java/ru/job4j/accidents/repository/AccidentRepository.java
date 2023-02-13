package ru.job4j.accidents.repository;

import ru.job4j.accidents.model.Accident;

import java.util.Collection;
import java.util.Optional;

public interface AccidentRepository {
    void add(Accident accident);

    Collection<Accident> findAll();

    boolean update(Accident accident);

    Optional<Accident> findById(int id);
}
