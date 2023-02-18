package ru.job4j.accidents.model;

import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Accident {
    @EqualsAndHashCode.Include
    @NonNull
    private int id;
    @NonNull
    private String name;
    @NonNull
    private String text;
    @NonNull
    private String address;
    @NonNull
    private AccidentType type;
    private Set<Rule> rules;
}