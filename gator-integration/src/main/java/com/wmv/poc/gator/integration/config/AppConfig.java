package com.wmv.poc.gator.integration.config;

import com.mangofactory.swagger.plugin.EnableSwagger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = { "com.wmv.poc.gator.integration","com.wmv.poc.gator.integration.camel.routes" ,"om.mangofactory.swagger.configuration"})
@EnableSwagger
@Import({CamelConfig.class, SwaggerConfig.class})
class AppConfig {
}