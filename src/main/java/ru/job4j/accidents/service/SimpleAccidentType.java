package ru.job4j.accidents.service;

import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.hbm.AccidentTypeHibernate;
import ru.job4j.accidents.repository.AccidentTypeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SimpleAccidentType implements AccidentTypeService {

    private final AccidentTypeRepository accidentTypeRepository;
    public SimpleAccidentType(AccidentTypeHibernate accidentTypeRepository) {
        this.accidentTypeRepository = accidentTypeRepository;
    }

    @Override
    public void add(AccidentType accidentType) {
        accidentTypeRepository.add(accidentType);
    }

    @Override
    public List<AccidentType> findAll() {
        return accidentTypeRepository.findAll();
    }

    @Override
    public Optional<AccidentType> findById(int id) {
        return accidentTypeRepository.findById(id);
    }
}
