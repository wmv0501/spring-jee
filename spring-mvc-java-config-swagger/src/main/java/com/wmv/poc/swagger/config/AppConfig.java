package com.wmv.poc.swagger.config;

import com.mangofactory.swagger.plugin.EnableSwagger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "com.wmv.poc.swagger" ,"om.mangofactory.swagger.configuration"})
@EnableSwagger
class AppConfig {
}