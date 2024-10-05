package com.coffeemachine;

import org.apache.logging.log4j.spi.LoggerContext;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy

public class CoffeeMachineApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoffeeMachineApplication.class, args);
    }

}
