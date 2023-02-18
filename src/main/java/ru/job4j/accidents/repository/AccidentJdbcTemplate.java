package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;

import java.sql.PreparedStatement;
import java.util.*;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class AccidentJdbcTemplate implements AccidentRepository {

    private final JdbcTemplate jdbc;
    private final static String INSERT_ACCIDENT_SQL = "insert into accident (name, text, address, type_id) values (?, ?, ?, ?)";
    private final static String INSERT_RULE_ACCIDENT_SQL = "insert into rule_accident (rule_id, accident_id) values (?, ?)";
    private final static String SELECT_ACCIDENT_SQL = "select * from accident";
    private final static String FIND_BY_ID = "select * from accident where id = ?";
    private final static String SELECT_RULE_BY_ACCIDENT_SQL = "select rule_id from rule_accident where accident_id = ?";
    private final static String SELECT_ACCIDENT_TYPE_SQL = "select * from type where id = ?";

    private final static String SELECT_RULE_SQL = "select * from rule where id = ?";
    private final static String UPDATE_ACCIDENT_SQL = "update accident set name = ?, text = ?, address = ?, type_id = ? where id = ?";
    private final static String DELETE_RULE_ACCIDENT_SQL = "delete from rule_accident where accident_id = ?";


    @Override
    public Accident add(Accident accident) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(INSERT_ACCIDENT_SQL, new String[]{"id"});
            ps.setString(1, accident.getName());
            ps.setString(2, accident.getText());
            ps.setString(3, accident.getAddress());
            ps.setInt(4, accident.getType().getId());
            return ps;
        }, keyHolder);
        int id = (Integer) keyHolder.getKey();
        accident.setId(id);
        addRule(accident.getRules(), id);
        return accident;
    }

    @Override
    public List<Accident> findAll() {
        return jdbc.query(SELECT_ACCIDENT_SQL,
                (rs, row) -> {
                    int id = rs.getInt("id");
                    Accident accident = new Accident(
                            id,
                            rs.getString("name"),
                            rs.getString("text"),
                            rs.getString("address"),
                            getAccidentType(rs.getInt("type_id")));
                    accident.setRules(getRules(id));
                    return accident;
                });
    }

    @Override
    public void update(Accident accident) {
        int id = accident.getId();
        jdbc.update(UPDATE_ACCIDENT_SQL,
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getType().getId(),
                id);
        jdbc.update(DELETE_RULE_ACCIDENT_SQL,
                accident.getId()
        );
        addRule(accident.getRules(), accident.getId());
    }

    @Override
    public Optional<Accident> findById(int id) {
        Accident result = jdbc.queryForObject(FIND_BY_ID,
                (rs, rowNum) -> new Accident(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("text"),
                        rs.getString("address"),
                        getAccidentType(rs.getInt("type_id"))), id);
        return Optional.ofNullable(result);
    }

    private AccidentType getAccidentType(int typeId) {
        return jdbc.queryForObject(SELECT_ACCIDENT_TYPE_SQL,
                (rs, rowNum) -> new AccidentType(
                        rs.getInt("id"),
                        rs.getString("name")), typeId);
    }

    private List<Integer> getRuleIds(int accidentId) {
        return jdbc.query(SELECT_RULE_BY_ACCIDENT_SQL,
                (rs, rowNum) -> rs.getInt("rule_id"), accidentId);
    }

    private Set<Rule> getRules(int accidentId) {
        return getRuleIds(accidentId)
                .stream()
                .map((id) -> jdbc.queryForObject(SELECT_RULE_SQL,
                        (rs, rowNum) ->
                                new Rule(rs.getInt("id"),
                                        rs.getString("name")), id))
                .collect(Collectors.toSet());
    }

    private void addRule(Set<Rule> rules, int accidentId) {
        rules.stream()
                .map(Rule::getId)
                .forEach(i -> jdbc.update(INSERT_RULE_ACCIDENT_SQL, i, accidentId));
    }

}
