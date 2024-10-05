package com.coffeemachine.exceptions;

public class FactoryElementNotFoundException extends RuntimeException {
    public FactoryElementNotFoundException(String mapperClassName) {
        super(String.format("Mapper for dto class %s not found", mapperClassName));
    }
}
