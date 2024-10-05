package com.coffeemachine.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

        @Value("${swagger.api.version}")
        private String version;

        @Bean
        public OpenAPI customOpenApiConfig(){
            OpenAPI openAPI = new OpenAPI();
            openAPI.info(new Info()
                    .title("Coffee machine API")
                    .description("This api describes all endpoints for coffee machine")
                    .version(version));
            return openAPI;
        }
}

