package com.coffeemachine.mappers;

import com.coffeemachine.dto.ReceiptDto;
import com.coffeemachine.entities.Receipt;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReceiptMapper extends SimpleMapper<ReceiptDto, Receipt> {

    @Override
    @Mapping(source = "ingredient.name", target = "ingredientName")
    @InheritInverseConfiguration(name = "toEntity")
    ReceiptDto toDto(Receipt entity);

    @Override
    Receipt toEntity(ReceiptDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Receipt partialUpdate(ReceiptDto receiptDto, @MappingTarget Receipt receipt);
}