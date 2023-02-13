package ru.job4j.accidents.service;


import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentMem;

import java.util.Collection;
import java.util.Optional;

@Service
public class SimpleAccidentService implements AccidentService {

    private final AccidentMem accidentMem;

    public SimpleAccidentService(AccidentMem accidentMem) {
        this.accidentMem = accidentMem;
    }

    @Override
    public void add(Accident accident) {
        accidentMem.add(accident);
    }

    @Override
    public Collection<Accident> findAll() {
        return accidentMem.findAll();
    }

    @Override
    public boolean update(Accident accident) {
        return accidentMem.update(accident);
    }

    @Override
    public Optional<Accident> findById(int id) {
        return accidentMem.findById(id);
    }
}
