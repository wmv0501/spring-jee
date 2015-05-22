package com.wmv.poc.gator.integration.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

/**
 * @author wvergara, created on 5/19/15.
 */
public abstract class AbstractJmsRouteBuilder<T> extends RouteBuilder {
    private static final Logger log = LoggerFactory.getLogger(AbstractJmsRouteBuilder.class);

    @Autowired
    private JmsTemplate template;

    protected void send(Object objectMessage) {
        log.info("Sending JMS message to:{}", getDestinationName());
        template.convertAndSend(getDestinationName(), objectMessage);
    }
    abstract protected String getDestinationName();

}
