package com.coffeemachine.dto.exceptions;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
@NoArgsConstructor
public class ValidationExceptionResponseDto extends ExceptionResponseDto {

    private List<ValidationExceptionDto> trace;

    public ValidationExceptionResponseDto(String path, String error, Integer status, List<ValidationExceptionDto> trace) {
        super(path, error, status);
        this.trace = trace;
    }
}
