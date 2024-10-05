package com.coffeemachine.api;

import com.coffeemachine.dto.client_api.DrinkCookRequestDto;
import com.coffeemachine.dto.client_api.DrinkRatingResponseDto;
import com.coffeemachine.dto.client_api.DrinkResponseDto;
import com.coffeemachine.entities.Drink;
import com.coffeemachine.entities.Rating;
import com.coffeemachine.service.CoffeeMachineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v${machine.api.version}/client")
@RequiredArgsConstructor
@Tag(name = "Client API", description = "API for customer interaction")
public class ClientApi {

    private final CoffeeMachineService coffeeMachineService;

    @PutMapping("/coffee")
    @Operation(summary = "Cook coffee")
    public String cookCoffee(@Valid @RequestBody DrinkCookRequestDto drinkCookRequestDto){
        return coffeeMachineService.cookCoffee(drinkCookRequestDto);
    }

    @GetMapping("/menu")
    @Operation(summary = "Get coffee machine menu")
    public List<DrinkResponseDto> getMenu(){
        return coffeeMachineService.getAllElements(Drink.class, DrinkResponseDto.class);
    }

    @GetMapping("/rating")
    @Operation(summary = "Get drinks rating")
    public List<DrinkRatingResponseDto> getRating(){
        return coffeeMachineService.getAllElements(Rating.class, DrinkRatingResponseDto.class);
    }

}
