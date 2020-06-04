package com.amsidh.mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    Parameter authHeader = new ParameterBuilder().parameterType("header").name("Authorization")
            .modelRef(new ModelRef("string")).defaultValue("Bearer XXXXXXXXXXXXXXXXXXXXX")// Base64.getEncoder().encodeToString("amsidhlokhande:password".getBytes()))
            .required(true) // for compulsory
            .build();

    @Bean
    public Docket getDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                //.securitySchemes(asList(apiKey()))
                .globalOperationParameters(Collections.singletonList(authHeader));

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Account-WS micro service")
                .description("This is Account-WS micro service application")
                .version("0.0.1-SNAPSHOT")
                .build();
    }
}
