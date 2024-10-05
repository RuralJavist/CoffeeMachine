package com.coffeemachine.dto.admin_api;

import com.coffeemachine.dto.IngredientDto;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Setter
@Getter
@NoArgsConstructor
public class NewIngredientsRequestDto implements Serializable {

    @NotEmpty(message = "Ingredients data can`t be null or empty")
    @NotNull(message = "Ingredients data can`t be null")
    @Valid
    private List<IngredientDto> ingredients;

}
