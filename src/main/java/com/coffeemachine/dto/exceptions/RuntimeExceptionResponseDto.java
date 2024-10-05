package com.coffeemachine.dto.exceptions;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;


@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@NoArgsConstructor
@Getter
public class RuntimeExceptionResponseDto extends ExceptionResponseDto{
    private String message;
    public RuntimeExceptionResponseDto(String path, String error, Integer status, String message) {
        super(path, error, status);
        this.message = message;
    }
}
