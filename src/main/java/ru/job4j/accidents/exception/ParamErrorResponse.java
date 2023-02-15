package ru.job4j.accidents.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ParamErrorResponse {

    private String message;
    private long timestamp;
}
