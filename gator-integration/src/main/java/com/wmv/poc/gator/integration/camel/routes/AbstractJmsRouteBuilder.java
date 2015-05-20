package com.wmv.poc.gator.integration.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

/**
 * @author wvergara, created on 5/19/15.
 */
public abstract class AbstractJmsRouteBuilder<T> extends RouteBuilder {

    @Autowired
    private JmsTemplate template;

    protected void send(T objectMessage){
        template.convertAndSend(getDestinationName(), objectMessage);
    }

    abstract String getDestinationName();

}
