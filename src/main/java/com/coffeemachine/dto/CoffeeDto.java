package com.coffeemachine.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link com.coffeemachine.entities.Drink}
 */
@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@NoArgsConstructor
public class CoffeeDto implements Serializable {
    @Schema(example = "latte")
    @Pattern(regexp = "^[a-zA-Z\\s]{3,15}$", message = "Invalid coffee name")
    private String name;
}