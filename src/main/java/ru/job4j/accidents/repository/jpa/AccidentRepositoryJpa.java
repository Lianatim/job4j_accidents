package ru.job4j.accidents.repository.jpa;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accidents.model.Accident;

import java.util.List;

public interface AccidentRepositoryJpa extends CrudRepository<Accident, Integer> {
    List<Accident> findAll();
}
