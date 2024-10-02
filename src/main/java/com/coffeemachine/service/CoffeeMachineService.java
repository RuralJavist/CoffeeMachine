package com.coffeemachine.service;

import com.coffeemachine.dto.admin_api.CoffeeNewDrinkDto;
import com.coffeemachine.dto.admin_api.IngredientDto;
import com.coffeemachine.entities.Drink;
import com.coffeemachine.entities.Ingredient;
import com.coffeemachine.entities.Rating;
import com.coffeemachine.entities.Receipt;
import com.coffeemachine.exceptions.DrinkAddingException;
import com.coffeemachine.exceptions.IngredientTankCapacityException;
import com.coffeemachine.repositories.DrinkRepository;
import com.coffeemachine.repositories.IngredientRepository;
import com.coffeemachine.repositories.ReceiptRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CoffeeMachineService {

    private static final long COOK_PROCESS_TIME = 2000L;

    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private final IngredientRepository ingredientRepository;
    private final DrinkRepository drinkRepository;
    private final ReceiptRepository receiptRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String cookCoffee(Drink drink, int tankCoefficient){
        Set<Receipt> ingredients = drink.getReceiptSet();
        ingredients.forEach(el -> {
            int remainderMl = el.getIngredient().getTankRemainderMl() - (el.getTankConsumptionMl()*tankCoefficient);
            if (remainderMl < 0){
                throw new IngredientTankCapacityException(el.getIngredient().getName());
            }
            el.getIngredient().setTankRemainderMl(remainderMl);
        });
        drink.getRating().setRating(drink.getRating().getRating()+1);
        drinkRepository.save(drink);
        log.info("Cook coffee action is started!\nCooking......");
        executorService.schedule(() ->
            log.info("Process finished, drink - {} ready!", drink.getName())
        , COOK_PROCESS_TIME, TimeUnit.MILLISECONDS);

        return String.format("Take %s, carefully hot.", drink.getName());
    }

    @Transactional
    public void addDrink(CoffeeNewDrinkDto coffeeNewDrinkDto){
        Set<String> ingredients = coffeeNewDrinkDto.getReceiptSet().stream()
                .map(CoffeeNewDrinkDto.ReceiptDto::getIngredientName).collect(Collectors.toSet());
        if (checkIngredients(ingredients)){
            Drink drink = new Drink(coffeeNewDrinkDto.getName(), new Rating());

            Set<Receipt> receipts = coffeeNewDrinkDto.getReceiptSet().stream().map(el ->
                    new Receipt(drink, new Ingredient(el.getIngredientName()) ,el.getPercentageRatio()))
                    .collect(Collectors.toSet());

            drink.setReceiptSet(receipts);
            drinkRepository.save(drink);
        }
    }

    //тут скорее всего проверить на уникальность.
    //дописать:
    //-это + удаление, дописать еще очистку раз в 5 лет, ограничение, и swagger
    //подумать мб над безопасностью
    @Transactional
    public void addIngredients(List<IngredientDto> ingredients){

        ingredientRepository.saveAll()
    }

    private boolean checkIngredients(Set<String> ingredients){
        for (String ingredient : ingredients){
            Ingredient ingredientFromBd = ingredientRepository.findByName(ingredient);
            if (ingredientFromBd == null){
                throw new DrinkAddingException(String.format("Cant add new drink, " +
                        "ingredient %s not found", ingredient));
            }
        }
        return true;
    }





}
