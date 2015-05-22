package com.wmv.poc.gator.integration.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jms.core.JmsTemplate;

/**
 * @author wvergara, created on 5/20/15
 */
@PropertySource("classpath:/jms-config.properties")
public class JmsConfiguration {

    @Autowired
    private Environment env;

    private static final String JMS_BROKER_PROP = "jms.broker.url";

    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory());
        return jmsTemplate;
    }

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL(env.getProperty(JMS_BROKER_PROP));
        return activeMQConnectionFactory;
    }
}
