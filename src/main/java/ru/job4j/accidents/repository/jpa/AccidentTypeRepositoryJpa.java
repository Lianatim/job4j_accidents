package ru.job4j.accidents.repository.jpa;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accidents.model.AccidentType;

import java.util.List;

public interface AccidentTypeRepositoryJpa extends CrudRepository<AccidentType, Integer> {
    List<AccidentType> findAll();
}
