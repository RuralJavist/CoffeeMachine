package com.coffeemachine.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.coffeemachine.entities.Receipt}
 */
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
public class ReceiptDto {

        @NotNull(message = "Ingredient name can`t be null")
        @Schema(example = "milk")
        @Pattern(regexp = "^[a-zA-Z\\s]{3,15}$", message = "Invalid ingredient name")
        @JsonProperty("ingredient_name")
        private String ingredientName;

        @Schema(example = "10")
        @NotNull(message = "Receipt ingredient ratio can`t be null")
        @JsonProperty("percentage_ratio")
        private Integer percentageRatio;
}