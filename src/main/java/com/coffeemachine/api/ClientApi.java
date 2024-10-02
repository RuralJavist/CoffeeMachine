package com.coffeemachine.api;

import com.coffeemachine.dto.client_api.CoffeeCookRequestDto;
import com.coffeemachine.dto.client_api.CoffeeRatingResponseDto;
import com.coffeemachine.dto.client_api.DrinkResponseDto;
import com.coffeemachine.facadies.ClientApiFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v${service.version}/client")
@RequiredArgsConstructor
public class ClientApi {

    private final ClientApiFacade clientApiFacade;

    @PutMapping("/coffee")
    public String cookCoffee(@RequestBody CoffeeCookRequestDto coffeeCookRequestDto){
        return clientApiFacade.cookCoffeeForClient(coffeeCookRequestDto);
    }

    @GetMapping("/coffee")
    public List<DrinkResponseDto> getDrinks(){
        return clientApiFacade.getDrinks();
    }

    @GetMapping("/rating")
    public List<CoffeeRatingResponseDto> getRating(){
        return clientApiFacade.getRatings();
    }

}
