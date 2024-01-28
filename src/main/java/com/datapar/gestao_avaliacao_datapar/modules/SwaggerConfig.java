package com.datapar.gestao_avaliacao_datapar.modules;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI(){

        return new OpenAPI()
        .info( new Info().title("Gestão de Satisfação").description("API responsável pela gestão de satisfação dos usuaário.").version("1"));
    }

}
