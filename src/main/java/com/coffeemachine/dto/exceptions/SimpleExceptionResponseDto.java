package com.coffeemachine.dto.exceptions;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SimpleExceptionResponseDto {

    String instant;
    String path;
    String error;
    Integer status;
    String message;

    public SimpleExceptionResponseDto(String path, String error, Integer status, String message) {
        this.instant = LocalDateTime.now().toString();
        this.path = path;
        this.error = error;
        this.status = status;
        this.message = message;
    }

    public SimpleExceptionResponseDto(String path, String error, Integer status) {
        this.instant = LocalDateTime.now().toString();
        this.path = path;
        this.error = error;
        this.status = status;
    }
}
