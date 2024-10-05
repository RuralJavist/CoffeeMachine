package com.coffeemachine.dto.client_api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@NoArgsConstructor
public class DrinkCookRequestDto implements Serializable {

    @NotNull(message = "Coffee name can`t be null")
    @Pattern(regexp = "^[a-zA-Z\\s]{3,15}$", message = "Invalid coffee name")
    @Schema(example = "latte")
    private String name;

    @JsonProperty("volume")
    @Pattern(regexp = "^[a-z]{3,10}$", message = "Invalid coffee name")
    @Schema(example = "small")
    @NotNull(message = "Volume type can`t be null")
    private String volumeType;

}