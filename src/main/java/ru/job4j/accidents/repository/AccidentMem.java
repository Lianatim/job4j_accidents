package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem implements AccidentRepository {

    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private static AtomicInteger count = new AtomicInteger(0);


    public AccidentMem() {
        accidents.put(count.incrementAndGet(), new Accident(count.get(), "name1", "text1", "address1"));
        accidents.put(count.incrementAndGet(), new Accident(count.get(), "name2", "text2", "address2"));
    }

    @Override
    public void add(Accident accident) {
        accidents.putIfAbsent(accident.getId(), accident);
    }

    @Override
    public Collection<Accident> findAll() {
        return accidents.values();
    }

    @Override
    public boolean update(Accident accident) {
        return accidents.replace(accident.getId(), accidents.get(accident.getId()), accident);
    }
}