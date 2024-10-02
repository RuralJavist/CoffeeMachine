package com.coffeemachine.exceptions;

public class IngredientAlreadyExist extends RuntimeException{

    public IngredientAlreadyExist(String message) {
        super(message);
    }
}
