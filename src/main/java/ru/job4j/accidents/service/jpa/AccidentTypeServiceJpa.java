package ru.job4j.accidents.service.jpa;

import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.jpa.AccidentTypeRepositoryJpa;
import ru.job4j.accidents.service.AccidentTypeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccidentTypeServiceJpa implements AccidentTypeService {

    private final AccidentTypeRepositoryJpa accidentTypeRepository;

    public AccidentTypeServiceJpa(AccidentTypeRepositoryJpa accidentTypeRepository) {
        this.accidentTypeRepository = accidentTypeRepository;
    }


    @Override
    public void add(AccidentType accidentType) {
        accidentTypeRepository.save(accidentType);
    }

    @Override
    public List<AccidentType> findAll() {
        List<AccidentType> accidentTypes = new ArrayList<>();
        accidentTypeRepository.findAll().forEach(accidentTypes::add);
        return accidentTypes;
    }

    @Override
    public Optional<AccidentType> findById(int id) {
        return accidentTypeRepository.findById(id);
    }
}
