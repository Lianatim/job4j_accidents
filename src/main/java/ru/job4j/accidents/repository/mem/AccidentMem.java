package ru.job4j.accidents.repository.mem;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.AccidentRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem implements AccidentRepository {

    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private static AtomicInteger count = new AtomicInteger(0);


    public AccidentMem() {
        accidents.put(count.incrementAndGet(), new Accident(count.get(), "name1", "text1", "address1",
                new AccidentType(count.get(), "Превышение скорости"), Set.of(new Rule(count.get(), "Статья. 1"))));
        accidents.put(count.incrementAndGet(), new Accident(count.get(), "name2", "text2", "address2",
                new AccidentType(count.get(), "Превышение скорости"), Set.of(new Rule(count.get(), "Статья. 1"))));
    }

    @Override
    public Accident add(Accident accident) {
       return accidents.putIfAbsent(accident.getId(), accident);
    }

    @Override
    public List<Accident> findAll() {
        return accidents.values().stream().toList();
    }

    @Override
    public void update(Accident accident) {
        accidents.replace(accident.getId(), accidents.get(accident.getId()), accident);
    }

    @Override
    public Optional<Accident> findById(int id) {
        return Optional.ofNullable(accidents.get(id));
    }
}
