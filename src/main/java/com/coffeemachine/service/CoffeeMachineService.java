package com.coffeemachine.service;

import com.coffeemachine.dto.admin_api.NewCoffeeRequestDto;
import com.coffeemachine.dto.IngredientDto;
import com.coffeemachine.dto.client_api.DrinkCookRequestDto;
import com.coffeemachine.entities.Drink;
import com.coffeemachine.entities.Ingredient;
import com.coffeemachine.entities.Rating;
import com.coffeemachine.entities.Receipt;
import com.coffeemachine.exceptions.IngredientNotFoundException;
import com.coffeemachine.exceptions.IngredientTankCapacityException;
import com.coffeemachine.exceptions.RecipeIngredientsUniqueException;
import com.coffeemachine.exceptions.RecipePreparationException;
import com.coffeemachine.repositories.DrinkRepository;
import com.coffeemachine.repositories.IngredientRepository;
import com.coffeemachine.repositories.RatingRepository;
import com.coffeemachine.repositories.ReceiptRepository;
import com.coffeemachine.service.utils.CoffeeMachineServiceUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
@RequiredArgsConstructor
public class CoffeeMachineService {

    private static final long COOK_PROCESS_TIME = 2000L;

    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private final CoffeeMachineServiceUtils coffeeMachineServiceUtils;
    private final DrinkRepository drinkRepository;
    private final IngredientRepository ingredientRepository;
    private final ReceiptRepository receiptRepository;
    private final RatingRepository ratingRepository;


    @SneakyThrows
    @Transactional()
    public String cookCoffee(DrinkCookRequestDto drinkCookRequestDto){
        Drink currentDrink = coffeeMachineServiceUtils.checkDrinkOnExist(drinkCookRequestDto.getName());
        int selectVolume = coffeeMachineServiceUtils.getSelectVolume(drinkCookRequestDto.getVolumeType());
        List<Receipt> receiptElements = currentDrink.getReceipts();
        receiptElements.forEach(el -> {
            int remainderMl = (int) (el.getIngredient().getTankRemainderMl() - ((double) el.getPercentageRatio()/100)*selectVolume);
            if (remainderMl < 0){
                throw new IngredientTankCapacityException(el.getIngredient().getName());
            }
            el.getIngredient().setTankRemainderMl(remainderMl);
        });
        currentDrink.getRating().setRating(currentDrink.getRating().getRating()+1);
        drinkRepository.save(currentDrink);
        log.info("Cook coffee action is started!\nCooking......");
        executorService.schedule(() ->
            log.info("Process finished, drink - {} ready!", currentDrink.getName())
        , COOK_PROCESS_TIME, TimeUnit.MILLISECONDS).get();
        return String.format("Take %s, carefully hot.", currentDrink.getName());
    }

    @Transactional
    public void addCoffee(NewCoffeeRequestDto newCoffeeRequestDto) {
        if (coffeeMachineServiceUtils.checkIngredientsInReceipt(newCoffeeRequestDto.getReceipts())) {
            AtomicInteger receiptPercentCounter = new AtomicInteger();
            Drink drink = drinkRepository.save(new Drink(newCoffeeRequestDto.getName(), new Rating()));
            List<Receipt> receipts = newCoffeeRequestDto.getReceipts().stream().map(el -> {
                Ingredient ingredient = ingredientRepository.findByName(el.getIngredientName());
                if (ingredient == null) {
                    throw new IngredientNotFoundException(el.getIngredientName());
                }
                receiptPercentCounter.getAndAccumulate(el.getPercentageRatio(), Integer::sum);
                return new Receipt(drink, ingredient, el.getPercentageRatio());
            }).toList();
            if (receiptPercentCounter.get() != 100){
                throw new RecipePreparationException();
            }
            receiptRepository.saveAll(receipts);
        }
    }

    //тут скорее всего проверить на уникальность.
    //дописать:
    //-это + удаление, дописать еще очистку раз в 5 лет, ограничение, и swagger
    //подумать мб над безопасностью
    @Transactional
    public void addIngredients(List<IngredientDto> ingredients){
        List<Ingredient> ingredientsEntity = coffeeMachineServiceUtils.dtoConverter(ingredients);
        ingredientRepository.saveAll(ingredientsEntity);
    }

    @Transactional
    public void updateTanks(){
        List<Ingredient> ingredients = ingredientRepository.findAll();
        ingredients.forEach(el -> el.setTankRemainderMl(Ingredient.BASE_TANK_CAPACITY_ML.intValue()));
        ingredientRepository.saveAll(ingredients);
    }

    @Transactional
    public <T> void deleteElement(List<Long> idsList, Class<T> targetEntity){
        coffeeMachineServiceUtils.deleteElements(idsList, targetEntity);
    }

    @Transactional
    public <T, R> List<T> getAllElements(Class<R> targetEntity, Class<T> targetDto){
        List<R> entityList = coffeeMachineServiceUtils.getElements(targetEntity).stream().sorted().toList();
        return coffeeMachineServiceUtils.entityConverter(targetDto, entityList);
    }
}
