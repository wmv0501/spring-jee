package com.wmv.sample.springmvjc.configuration;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.TransportConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.ConnectionFactory;
import java.net.URI;

/**
 * @author wvergara, created on 5/20/15
 */
@EnableJms
@Configuration
@PropertySource("classpath:/jms-config.properties")
public class JmsConfiguration {

    @Autowired
    private Environment env;

    private static final String JMS_BROKER_PROP = "jms.broker.url";
    private final String DEFAULT_DESTINATION = "poc.dest_01";


    @Bean // Strictly speaking this bean is not necessary as boot creates a default
    JmsListenerContainerFactory<?> myJmsContainerFactory() {
        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory(amqConnectionFactory()));
        return factory;
    }



    @Bean
    CachingConnectionFactory connectionFactory(ActiveMQConnectionFactory amqConnectionFactory){
        return new CachingConnectionFactory(amqConnectionFactory);
    }


    @Bean
    public ActiveMQConnectionFactory amqConnectionFactory() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL("tcp://localhost:61617");

        return activeMQConnectionFactory;
    }


    @Bean(name = "broker")
    public BrokerService setUpEmbeddedActiveMQBroker() throws Throwable {
        BrokerService broker = new BrokerService();

        TransportConnector connector = new TransportConnector();
        connector.setUri(new URI("tcp://localhost:61617"));
        broker.addConnector(connector);
        broker.setPersistent(false);
        broker.start();

        return broker;
    }

}
