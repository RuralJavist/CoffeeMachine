package com.coffeemachine.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@NoArgsConstructor
@Getter
@Setter
public class CurrentIngredientDto implements Serializable {
    @Schema(example = "2")
    private Long id;
    @Schema(example = "milk")
    private String name;
    @Schema(example = "999")
    private Long tankRemainderMl;
}
