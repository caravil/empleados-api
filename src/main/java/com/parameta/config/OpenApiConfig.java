package com.parameta.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;


@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Empleados API")
                        .version("1.0")
                        .description("API REST para la gestión de empleados - Prueba Técnica Parameta")
                        .contact(new Contact()
                                .name("Carlos Andrés Sánchez Avilés")
                                .email("tu-correo@correo.com")));
    }
}