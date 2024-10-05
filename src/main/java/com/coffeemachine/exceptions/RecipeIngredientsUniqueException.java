package com.coffeemachine.exceptions;

public class RecipeIngredientsUniqueException extends RuntimeException {
    public RecipeIngredientsUniqueException() {
        super("Receipt ingredients not unique");
    }
}
