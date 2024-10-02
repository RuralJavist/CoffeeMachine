package com.coffeemachine.dto.admin_api;

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
public class CoffeeNewDrinkDto implements Serializable {
    String name;

    @JsonProperty("receipt_components")
    Set<ReceiptDto> receiptSet;
    /**
     * DTO for {@link com.coffeemachine.entities.Receipt}
     */
    @Value
    public static class ReceiptDto implements Serializable {
        String ingredientName;
        Integer percentageRatio;
    }
}