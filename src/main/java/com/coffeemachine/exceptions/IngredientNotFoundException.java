package com.coffeemachine.exceptions;

public class IngredientNotFoundException extends RuntimeException {
    public IngredientNotFoundException(String name) {
        super(String.format("Ingredient with name %s not found", name));
    }
}
