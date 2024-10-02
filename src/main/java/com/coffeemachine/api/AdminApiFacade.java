package com.coffeemachine.api;

import com.coffeemachine.dto.admin_api.CoffeeNewDrinkDto;
import com.coffeemachine.dto.admin_api.IngredientsAddingDto;
import com.coffeemachine.entities.Ingredient;
import com.coffeemachine.service.CoffeeMachineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v${service.version}/admin")
@RequiredArgsConstructor
public class AdminApiFacade {

    private final CoffeeMachineService coffeeMachineService;

    @PostMapping("/coffee")
    public void addNewDrink(CoffeeNewDrinkDto coffeeNewDrinkDto){
        coffeeMachineService.addDrink(coffeeNewDrinkDto);
    }

    @PostMapping("/ingredients")
    public void addNewIngredients(IngredientsAddingDto ingredients){
        coffeeMachineService.
    }

}
