package com.coffeemachine.dto.client_api;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.coffeemachine.entities.Rating}
 */
@Value
public class CoffeeRatingResponseDto implements Serializable {
    Long rating;
    String drinkName;
}