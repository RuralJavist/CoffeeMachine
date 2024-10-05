package com.coffeemachine.api;

import com.coffeemachine.dto.CurrentIngredientDto;
import com.coffeemachine.dto.admin_api.*;
import com.coffeemachine.entities.Drink;
import com.coffeemachine.entities.Ingredient;
import com.coffeemachine.service.CoffeeMachineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v${machine.api.version}/admin")
@RequiredArgsConstructor
@Tag(name = "Admin API", description = "API for adding something in coffee machine")
public class AdminApi {

    private final CoffeeMachineService coffeeMachineService;

    @PostMapping("/drink")
    @Operation(summary = "Add new coffee in machine")
    public void addNewCoffee(@Valid @RequestBody NewCoffeeRequestDto newCoffeeRequestDto){
        coffeeMachineService.addCoffee(newCoffeeRequestDto);
    }

    @GetMapping("/ingredients")
    @Operation(summary = "Get all ingredients in machine")
    public List<CurrentIngredientDto> getIngredients(){
        return coffeeMachineService.getAllElements(Ingredient.class, CurrentIngredientDto.class);
    }

    @PostMapping("/ingredients")
    @Operation(summary = "Add new ingredients in machine")
    public void addNewIngredients(@Valid @RequestBody NewIngredientsRequestDto ingredients){
        coffeeMachineService.addIngredients(ingredients.getIngredients());
    }

    @DeleteMapping("/ingredients")
    @Operation(summary = "Delete ingredients from machine")
    public void deleteIngredients(@Valid @RequestBody DeleteElementsRequestDto deleteElementsRequestDto){
        coffeeMachineService.deleteElement(deleteElementsRequestDto.getIds(), Ingredient.class);
    }

    @DeleteMapping("/drink")
    @Operation(summary = "Delete drinks from machine")
    public void deleteDrinks(@Valid @RequestBody DeleteElementsRequestDto deleteElementsRequestDto){
        coffeeMachineService.deleteElement(deleteElementsRequestDto.getIds(), Drink.class);
    }

    @PutMapping("/tank")
    @Operation(summary = "Update tanks capacity")
    public void updateAllTanks(){
        coffeeMachineService.updateTanks();
    }

}
