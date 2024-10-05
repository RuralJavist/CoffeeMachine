package com.coffeemachine.service.utils;

import com.coffeemachine.dto.IngredientDto;
import com.coffeemachine.dto.ReceiptDto;
import com.coffeemachine.dto.CurrentIngredientDto;
import com.coffeemachine.dto.client_api.DrinkRatingResponseDto;
import com.coffeemachine.dto.client_api.DrinkResponseDto;
import com.coffeemachine.entities.Drink;
import com.coffeemachine.entities.Ingredient;
import com.coffeemachine.entities.Rating;
import com.coffeemachine.exceptions.DrinkNotFoundException;
import com.coffeemachine.exceptions.FactoryElementNotFoundException;
import com.coffeemachine.exceptions.RecipeIngredientsUniqueException;
import com.coffeemachine.exceptions.VolumeNotFoundException;
import com.coffeemachine.mappers.*;
import com.coffeemachine.repositories.DrinkRepository;
import com.coffeemachine.repositories.IngredientRepository;
import com.coffeemachine.repositories.RatingRepository;
import com.coffeemachine.repositories.SimpleRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class CoffeeMachineServiceUtils {
    private final MapperFactory mapperFactory;
    private final RepositoryFactory repositoryFactory;
    private final DrinkRepository drinkRepository;

    @SuppressWarnings("unchecked")
    public <T, R> List<R> dtoConverter(List<T> elementsDto){
        SimpleMapper<T, R> simpleMapper = (SimpleMapper<T, R>) mapperFactory.getMapper(elementsDto.get(0).getClass());
        return elementsDto.stream().map(simpleMapper::toEntity).toList();
    }

    public <T, R> List<T> entityConverter(Class<T> dtoTarget, List<R> elementsEntity){
        SimpleMapper<T, R> simpleMapper = mapperFactory.getMapper(dtoTarget);
        return elementsEntity.stream().map(simpleMapper::toDto).toList();
    }

    public <T> void deleteElements(List<Long> ids, Class<T> targetEntity){
        SimpleRepository<T, Long> repository = repositoryFactory.getRepository(targetEntity);
        repository.deleteAllById(ids);
    }

    public <T> List<T> getElements(Class<T> target){
        SimpleRepository<T, ?> repository = repositoryFactory.getRepository(target);
        return repository.findAll();
    }

    public Drink checkDrinkOnExist(String drinkName){
        Drink currentDrink = drinkRepository.findByName(drinkName);
        if (currentDrink == null) {
            throw new DrinkNotFoundException(drinkName);
        }
        else {
            return currentDrink;
        }
    }

    public boolean checkIngredientsInReceipt(List<ReceiptDto> receiptDtos){
        Set<ReceiptDto> setReceipt = new HashSet<>(receiptDtos);
        if (setReceipt.size() != receiptDtos.size()) {
            throw new RecipeIngredientsUniqueException();
        }
        else {
            return true;
        }
    }

    public int getSelectVolume(String volumeType){
        return drinkVolumeFactory(volumeType.toUpperCase());
    }

    private int drinkVolumeFactory(String type){
        return switch (type){
            case "SMALL" -> DrinkVolume.SMALL_BASIC.getTankCoefficient();
            case "MIDDLE" -> DrinkVolume.MIDDLE.getTankCoefficient();
            default -> throw new VolumeNotFoundException(type);
        };
    }

    @RequiredArgsConstructor
    @Getter
    enum DrinkVolume{
        SMALL_BASIC(150),
        MIDDLE(300);

        private final int tankCoefficient;
    }


    @Component
    @RequiredArgsConstructor
    @SuppressWarnings("unchecked")
    static class MapperFactory{
        private final IngredientMapper ingredientMapper;
        private final DrinkMapper drinkMapper;
        private final CurrentIngredientMapper currentIngredientMapper;
        private final ReceiptMapper receiptMapper;
        private final RatingMapper ratingMapper;

        public <T,R> SimpleMapper<T,R> getMapper(Class<T> dtoClass){
            if (dtoClass == IngredientDto.class){
                return (SimpleMapper<T, R>) ingredientMapper;
            }
            else if (dtoClass == CurrentIngredientDto.class){
                return (SimpleMapper<T, R>) currentIngredientMapper;
            }
            else if (dtoClass == DrinkResponseDto.class){
                return (SimpleMapper<T, R>) drinkMapper;
            }
            else if (dtoClass == DrinkRatingResponseDto.class){
                return (SimpleMapper<T, R>) ratingMapper;
            }
            else if (dtoClass == ReceiptDto.class){
                return (SimpleMapper<T, R>) receiptMapper;
            }
            else throw new FactoryElementNotFoundException(dtoClass.getName());
        }
    }


    @Component
    @RequiredArgsConstructor
    @SuppressWarnings("unchecked")
    static class RepositoryFactory{
        private final IngredientRepository ingredientRepository;
        private final DrinkRepository drinkRepository;
        private final RatingRepository ratingRepository;

        public <T, R> SimpleRepository<R, Long> getRepository(Class<T> targetClass){
            if(targetClass == Ingredient.class){
                return (SimpleRepository<R, Long>) ingredientRepository;
            }
            else if (targetClass == Drink.class){
                return (SimpleRepository<R, Long>) drinkRepository;
            }
            else if (targetClass == Rating.class){
                return (SimpleRepository<R, Long>) ratingRepository;
            }
            else throw new FactoryElementNotFoundException(targetClass.getSimpleName());
        }
    }

}
