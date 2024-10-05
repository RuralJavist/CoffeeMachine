package com.coffeemachine.mappers;

import com.coffeemachine.dto.IngredientDto;
import com.coffeemachine.entities.Ingredient;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface IngredientMapper extends SimpleMapper<IngredientDto, Ingredient> {

    @Override
    @Mapping(source = "name", target = "name")
    Ingredient toEntity(IngredientDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Ingredient partialUpdate(IngredientDto ingredientDto, @MappingTarget Ingredient ingredient);
}