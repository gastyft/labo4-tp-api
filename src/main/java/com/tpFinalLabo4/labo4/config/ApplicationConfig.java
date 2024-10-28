package com.tpFinalLabo4.labo4.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

  @Configuration
public class ApplicationConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("API de Plataforma de Cursos")
                        .description("Documentación de la API para la aplicación del TP de laboratorio 4")
                        .version("1.0.0")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Backend para el TP de laboratorio 4")
                        .url("https://swagger.io/docs/"));
//                .components(new Components().addSecuritySchemes("basicScheme",
//                                                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")))
//                .security(List.of(new SecurityRequirement().addList("basicScheme")));
    }

}