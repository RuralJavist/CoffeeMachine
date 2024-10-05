package com.coffeemachine.exceptions;

public class RecipePreparationException extends RuntimeException {
    public RecipePreparationException() {
        super("The recipe is not balanced correctly, all ingredients should add up to 100%.");
    }
}
