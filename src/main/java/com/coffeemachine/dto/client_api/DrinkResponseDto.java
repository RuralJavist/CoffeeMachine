package com.coffeemachine.dto.client_api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Value;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link com.coffeemachine.entities.Drink}
 */
@Value
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DrinkResponseDto implements Serializable {
    String name;

    @JsonProperty("ingredients")
    Set<ReceiptDto> receiptSet;

    /**
     * DTO for {@link com.coffeemachine.entities.Receipt}
     */
    @Value
    public static class ReceiptDto implements Serializable {
        String ingredientName;
    }
}