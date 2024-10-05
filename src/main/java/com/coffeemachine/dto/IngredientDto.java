package com.coffeemachine.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.coffeemachine.entities.Ingredient}
 */
@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@NoArgsConstructor
public class IngredientDto implements Serializable {

    @NotNull(message = "Ingredient name can`t be null")
    @Schema(example = "milk")
    @Pattern(regexp = "^[a-zA-Z\\s]{3,15}$", message = "Invalid ingredient name")
    private String name;
}