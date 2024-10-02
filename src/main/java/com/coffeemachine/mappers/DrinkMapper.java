package com.coffeemachine.mappers;

import com.coffeemachine.dto.client_api.DrinkResponseDto;
import com.coffeemachine.entities.Drink;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface DrinkMapper {
    Drink toEntity(DrinkResponseDto drinkResponseDto);

    @AfterMapping
    default void linkReceiptSet(@MappingTarget Drink drink) {
        drink.getReceiptSet().forEach(receiptSet -> receiptSet.setDrink(drink));
    }

    DrinkResponseDto toDto(Drink drink);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Drink partialUpdate(DrinkResponseDto drinkResponseDto, @MappingTarget Drink drink);
}