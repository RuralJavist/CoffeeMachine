package com.coffeemachine.exceptions;

public class IngredientTankCapacityException extends RuntimeException {

    public IngredientTankCapacityException(String ingredientName) {
        super(String.format("Tank is empty for ingredient - %s"));
    }
}
