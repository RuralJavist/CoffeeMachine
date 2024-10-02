package com.coffeemachine.facadies;

import com.coffeemachine.dto.client_api.CoffeeCookRequestDto;
import com.coffeemachine.dto.client_api.CoffeeRatingResponseDto;
import com.coffeemachine.dto.client_api.DrinkResponseDto;
import com.coffeemachine.entities.Drink;
import com.coffeemachine.exceptions.DrinkNotFoundException;
import com.coffeemachine.exceptions.VolumeNotFoundException;
import com.coffeemachine.mappers.DrinkMapper;
import com.coffeemachine.mappers.RatingMapper;
import com.coffeemachine.repositories.DrinkRepository;
import com.coffeemachine.repositories.RatingRepository;
import com.coffeemachine.repositories.ReceiptRepository;
import com.coffeemachine.service.CoffeeMachineService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ClientApiFacade {

    private final DrinkRepository drinkRepository;
    private final ReceiptRepository receiptRepository;
    private final CoffeeMachineService coffeeMachineService;
    private final RatingRepository ratingRepository;
    private final RatingMapper ratingMapper;
    private final DrinkMapper drinkMapper;

    @Transactional(readOnly = true)
    public String cookCoffeeForClient(CoffeeCookRequestDto requestDto){
        Drink drink = drinkRepository.findByName(requestDto.getName());
        if (drink == null) {
            throw new DrinkNotFoundException(requestDto.getName());
        }
        int tankCoefficient = drinkVolumeFactory(requestDto.getVolumeType());
        return coffeeMachineService.cookCoffee(drink, tankCoefficient);
    }

    @Transactional(readOnly = true)
    public List<CoffeeRatingResponseDto> getRatings(){
        return ratingRepository.findAll().stream().map(ratingMapper::toDto).toList();
    }

    @Transactional(readOnly = true)
    public List<DrinkResponseDto> getDrinks(){
        return drinkRepository.findAll().stream().map(drinkMapper::toDto).toList();
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
        SMALL_BASIC(1),
        MIDDLE(2);

        private final int tankCoefficient;
    }

}
