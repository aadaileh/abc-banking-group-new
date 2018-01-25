package com.abcbankinggroup.Configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * <h1>Swagger Configuration Class</h1>
 *
 * <p>
 * Contains the necessary configuration methods and annotations to create
 * the swagger documentation. It is essential to have this kind of documentation
 * to list all available services and RESTFul verbs to be use by client.
 * </p>
 *
 * @Author  Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
 * @version 1.0
 * @since   26.01.2018
 */
@Configuration
@EnableSwagger2
@EnableAutoConfiguration
public class SwaggerConfiguration {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.abcbankinggroup"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "ABC Banking Group RESTful API",
                "Some custom description of API.",
                "API TOS",
                "Terms of service",
                new Contact("Ahmed Al-Adaileh", null, "k1560383@kingston.ac.uk"),
                "License of API", "API license URL", Collections.emptyList());
    }
}