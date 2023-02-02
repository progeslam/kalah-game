package com.rebound.kalah.config;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


@Configuration
public class SpringFoxConfig {

    /**
     * Setting Up Swagger Using Springfox to generate documentation for a Spring REST API
     *
     * @return Docket bean the builder which is intended to be the primary interface into the swagger-springmvc framework
     */
    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .paths(regex("/.*"))
            .build();
    }

}