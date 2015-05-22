package com.wmv.poc.gator.integration.config;

import com.google.common.collect.ImmutableList;
import com.wmv.poc.gator.integration.camel.routes.FileCsvRouteBuilder;
import com.wmv.poc.gator.integration.camel.routes.MyFileRouteBuilder;
import com.wmv.poc.gator.integration.camel.routes.OrderJmsRouteBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.javaconfig.CamelConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

/**
 * Camel configuration, routes should be set set here
 *
 * @author wvergara, created on 5/18/15
 */
@ComponentScan(basePackages = {"com.wmv.poc.gator.integration.camel.routes"})
public class CamelConfig extends CamelConfiguration {


    @Autowired
    private FileCsvRouteBuilder fileCsvRouteBuilder;

    @Autowired
    private OrderJmsRouteBuilder jmsRouteBuilder;

    @Bean
    public MyFileRouteBuilder myFileRouteBuilderBean() {
        MyFileRouteBuilder myFileRouteBuilder = new MyFileRouteBuilder();
        myFileRouteBuilder.setFileSource("src/data?noop=true");
        myFileRouteBuilder.setFileDestination("file:target/messages/uk");
        myFileRouteBuilder.setAutoStartup(true);
        return myFileRouteBuilder;
    }

    @Override
    public List<RouteBuilder> routes() {
        return (List) ImmutableList.of(fileCsvRouteBuilder, jmsRouteBuilder);
    }


}