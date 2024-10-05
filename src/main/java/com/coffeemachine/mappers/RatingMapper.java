package com.coffeemachine.mappers;

import com.coffeemachine.dto.client_api.DrinkRatingResponseDto;
import com.coffeemachine.entities.Rating;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RatingMapper extends SimpleMapper<DrinkRatingResponseDto, Rating> {

    @Override
    @Mapping(target = "name", source = "drink.name")
    @Mapping(target = "rating", source = "rating")
    @InheritInverseConfiguration(name = "toEntity")
    DrinkRatingResponseDto toDto(Rating dto);

    @Override
    Rating toEntity(DrinkRatingResponseDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Rating partialUpdate(DrinkRatingResponseDto drinkRatingResponseDto, @MappingTarget Rating rating);

}