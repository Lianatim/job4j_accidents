package ru.job4j.accidents.service;


import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.hbm.AccidentHibernate;
import ru.job4j.accidents.repository.AccidentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SimpleAccidentService implements AccidentService {

    private final AccidentRepository accidentRepository;

    public SimpleAccidentService(AccidentHibernate accidentRepository) {
        this.accidentRepository = accidentRepository;
    }


    @Override
    public void add(Accident accident) {
        accidentRepository.add(accident);
    }

    @Override
    public List<Accident> findAll() {
        return accidentRepository.findAll();
    }

    @Override
    public void update(Accident accident) {
        accidentRepository.update(accident);
    }

    @Override
    public Optional<Accident> findById(int id) {
        return accidentRepository.findById(id);
    }
}
