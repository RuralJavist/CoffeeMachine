package com.coffeemachine.exceptions;

public class DrinkNotFoundException extends RuntimeException {
    public DrinkNotFoundException(String name) {
        super(String.format("Drink with name %s not found", name));
    }
}
