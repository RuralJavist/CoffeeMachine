package com.coffeemachine.mappers;

import com.coffeemachine.dto.CurrentIngredientDto;
import com.coffeemachine.entities.Ingredient;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CurrentIngredientMapper extends SimpleMapper<CurrentIngredientDto, Ingredient> {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Ingredient partialUpdate(CurrentIngredientDto currentIngredientDto, @MappingTarget Ingredient ingredient);

}