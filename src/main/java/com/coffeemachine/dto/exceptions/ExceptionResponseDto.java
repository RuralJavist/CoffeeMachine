package com.coffeemachine.dto.exceptions;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public abstract class ExceptionResponseDto implements Serializable {

    @Schema(example = "3423422434")
    private String instant;
    @Schema(example = "/api/some/")
    private String path;
    @Schema(example = "BAD_REQUEST/....")
    private String error;
    @Schema(example = "400")
    private Integer status;

    public ExceptionResponseDto(String path, String error, Integer status) {
        this.instant = LocalDateTime.now().toString();
        this.path = path;
        this.error = error;
        this.status = status;
    }

}
