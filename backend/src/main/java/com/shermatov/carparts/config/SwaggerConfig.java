package com.shermatov.carparts.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI carPartsOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Car Parts API")
                        .description("API documentation for Car Parts project")
                        .version("v1.0")
                )
                .externalDocs(new ExternalDocumentation()
                        .description("Project GitHub repository")
                        .url("https://github.com/shermatov/car-part")
                );
    }
}
