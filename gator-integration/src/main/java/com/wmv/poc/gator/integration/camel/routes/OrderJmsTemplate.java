package com.wmv.poc.gator.integration.camel.routes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;

/**
 * @author wvergara, created on 5/19/15.
 */
public class OrderJmsTemplate extends JmsTemplate {

    private final String DEFAUlT_DESTINATION = "poc.dest_01";

    public OrderJmsTemplate() {
        super.setDefaultDestinationName(DEFAUlT_DESTINATION);

    }

    @Qualifier("pooledConnectionFactory")
    @Autowired
    @Override
    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        super.setConnectionFactory(connectionFactory);
    }
}
