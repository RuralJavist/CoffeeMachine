package com.coffeemachine.mappers;

import com.coffeemachine.dto.client_api.DrinkResponseDto;
import com.coffeemachine.entities.Drink;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = ReceiptMapper.class)
public interface DrinkMapper extends SimpleMapper<DrinkResponseDto, Drink> {

    @Override
    @Mapping(source = "coffeeId", target = "id")
    @Mapping(source = "coffeeName", target = "name")
    @Mapping(source = "receiptList", target = "receipts")
    @InheritInverseConfiguration(name = "toDto")
    Drink toEntity(DrinkResponseDto dto);

    @Override
    DrinkResponseDto toDto(Drink entity);

    @AfterMapping
    default void linkReceiptSet(@MappingTarget Drink drink) {
        drink.getReceipts().forEach(receiptSet -> receiptSet.setDrink(drink));
    }
}