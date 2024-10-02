package com.coffeemachine.dto.exceptions;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ValidationResponseDto extends SimpleExceptionResponseDto {

    List<ValidationExceptionDto> trace;

    public ValidationResponseDto(String path, String error, Integer status, List<ValidationExceptionDto> trace) {
        super(path, error, status);
        this.trace = trace;
    }
}
