package com.coffeemachine.mappers;


public interface SimpleMapper<T, R> {

    R toEntity(T dto);

    T toDto(R entity);

    R partialUpdate(T dto, R entity);

}
