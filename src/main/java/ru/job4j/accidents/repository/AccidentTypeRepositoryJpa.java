package ru.job4j.accidents.repository;

import ru.job4j.accidents.model.AccidentType;
import org.springframework.data.repository.CrudRepository;

public interface AccidentTypeRepositoryJpa extends CrudRepository<AccidentType, Integer> {

}
