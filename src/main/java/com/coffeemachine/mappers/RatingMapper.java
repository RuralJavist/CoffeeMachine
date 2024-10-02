package com.coffeemachine.mappers;

import com.coffeemachine.dto.client_api.CoffeeRatingResponseDto;
import com.coffeemachine.entities.Rating;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RatingMapper {
    Rating toEntity(CoffeeRatingResponseDto coffeeRatingResponseDto);

    CoffeeRatingResponseDto toDto(Rating rating);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Rating partialUpdate(CoffeeRatingResponseDto coffeeRatingResponseDto, @MappingTarget Rating rating);
}