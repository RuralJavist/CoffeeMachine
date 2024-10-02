package com.coffeemachine.dto.client_api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.coffeemachine.entities.Drink}
 */
@Value
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Setter
@Getter
public class CoffeeCookRequestDto implements Serializable {

    String name;

    @JsonProperty("volume")
    String volumeType;

}