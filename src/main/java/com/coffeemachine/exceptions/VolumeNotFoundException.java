package com.coffeemachine.exceptions;

public class VolumeNotFoundException extends RuntimeException {

    public VolumeNotFoundException(String volumeType) {
        super(String.format("Drink volume with type %s not found", volumeType));
    }

}
