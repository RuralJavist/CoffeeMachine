package com.coffeemachine.dto.admin_api;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.coffeemachine.entities.Ingredient}
 */
@Value
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class IngredientDto implements Serializable {
    String name;
}