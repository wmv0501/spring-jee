package com.wmv.poc.gator.integration.config;

import com.mangofactory.swagger.plugin.EnableSwagger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableSwagger
@ComponentScan(basePackages = { "com.wmv.poc.gator.integration"})
@Import({CamelConfig.class, SwaggerConfig.class, JmsConfiguration.class})
class AppConfig {
}