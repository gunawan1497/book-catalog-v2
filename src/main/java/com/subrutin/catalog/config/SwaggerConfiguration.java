package com.subrutin.catalog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.ParameterType;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;

import java.util.Arrays;
import java.util.UUID;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
public class SwaggerConfiguration {

    private static final String REQUEST_ID = UUID.randomUUID().toString();

    private static final String USERNAME = "username";

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.subrutin.catalog.controller"))
                .paths(regex("/.*"))
                .build()
                .globalRequestParameters(Arrays.asList(
                        new RequestParameterBuilder()
                                .name(REQUEST_ID)
                                .description("Request ID")
                                .in(ParameterType.HEADER)
                                .required(true)
                                .query(builder -> builder.defaultValue(REQUEST_ID).model(m -> m.scalarModel(ScalarType.STRING))
                                .allowEmptyValue(false))
                                .build(),
                        new RequestParameterBuilder()
                                .name("USERNAME")
                                .description("Username")
                                .in(ParameterType.HEADER)
                                .required(true)
                                .query(builder -> builder.defaultValue(USERNAME).model(m -> m.scalarModel(ScalarType.STRING))
                                .allowEmptyValue(false))
                                .build()
                ))
                .apiInfo(new ApiInfoBuilder()
                        .title("Book Catalog API")
                        .description("Book Catalog API")
                        .version("1.0.0.0")
                        .license("Apache 2.0")
                        .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
                        .build())
                .genericModelSubstitutes(DeferredResult.class, ResponseEntity.class);
    }

    UiConfiguration uiConfiguration() {
        return UiConfigurationBuilder.builder()
                .displayRequestDuration(true)
                .validatorUrl(null)
                .build();
    }
}
