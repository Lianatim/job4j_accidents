package ru.job4j.accidents.service.jpa;

import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.jpa.AccidentRepositoryJpa;
import ru.job4j.accidents.service.AccidentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccidentServiceJpa implements AccidentService {

    private final AccidentRepositoryJpa accidentRepository;

    public AccidentServiceJpa(AccidentRepositoryJpa accidentRepository) {
        this.accidentRepository = accidentRepository;
    }

    @Override
    public void add(Accident accident) {
        accidentRepository.save(accident);
    }

    @Override
    public List<Accident> findAll() {
        List<Accident> accidents = new ArrayList<>();
        accidentRepository.findAll().forEach(accidents::add);
        return accidents;
    }

    @Override
    public void update(Accident accident) {
        accidentRepository.save(accident);
    }

    @Override
    public Optional<Accident> findById(int id) {
        return accidentRepository.findById(id);
    }
}
