package com.wmv.poc.gator.integration.config;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import com.wordnik.swagger.model.ApiInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;

/**
 * TODO: move swagger as pluggable module
 * @author wvergara, created on 5/18/15
 */
@ActiveProfiles("swagger")
@Configuration
public class SwaggerConfig {

    private SpringSwaggerConfig springSwaggerConfig;

    private final static String PROJECT_NAME = "Gator";

    @Autowired
    public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
        this.springSwaggerConfig = springSwaggerConfig;
    }

    @Bean
    public SwaggerSpringMvcPlugin customImplementation() {
        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig).apiInfo(
                apiInfo()).includePatterns("/wilson/.*");
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo("Gator API", PROJECT_NAME+" for Gator webservice",
                PROJECT_NAME+ " API terms of service", "gator@owens.com",
                PROJECT_NAME+" Gator API Licence Type", PROJECT_NAME +" API License URL");
        return apiInfo;
    }
}
