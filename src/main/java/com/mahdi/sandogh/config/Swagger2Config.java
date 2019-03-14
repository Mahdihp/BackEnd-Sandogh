package com.mahdi.sandogh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
//                .apis(withClassAnnotation(CrossOrigin.class))
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
//                .paths(PathSelectors.regex("/.*"))
                .paths(PathSelectors.any())
                .build();

    }

    @Bean
    public ApiInfo apiEndPointsInfo() {

        return new ApiInfoBuilder().title("REST API & Mostashar")
                .description("Employee Management REST API")
                .contact(new Contact("Mahdi Hosseinpour", "https://jobinja.ir/user/mahdihp", "mahdihp.devsc@gmail.com"))
                .licenseUrl("Mostasharapp.ir")
                .license("Apache 2.0")
                .version("1.0.0")
                .build();

    }
}
