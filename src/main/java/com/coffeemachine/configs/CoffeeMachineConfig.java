package com.coffeemachine.configs;

import com.coffeemachine.entities.RatingMetadata;
import com.coffeemachine.repositories.RatingMetaRepository;
import com.coffeemachine.service.CoffeeMachineService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@RequiredArgsConstructor
public class CoffeeMachineConfig {

    private final CoffeeMachineService coffeeMachineService;

    @PostConstruct
    public void init(){
        coffeeMachineService.checkRatingMetaInit();
    }

}
