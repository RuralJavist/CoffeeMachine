package com.coffeemachine.dto.exceptions;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AllArgsConstructor
public class ValidationExceptionDto {

    @Schema(example = "Main.class")
    private String location;
    @Schema(example = "Validation exception")
    private String message;
    @Schema(example = "MethodArgumentNotValidException")
    private String type;
}