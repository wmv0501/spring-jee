package com.wmv.poc.gator.integration.config;

import com.google.common.collect.ImmutableList;
import com.wmv.poc.gator.integration.camel.routes.FileCsvRouteBuilder;
import com.wmv.poc.gator.integration.camel.routes.OrderJmsRouteBuilder;
import com.wmv.poc.gator.integration.camel.routes.MyFileRouteBuilder;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.activemq.jms.pool.PooledConnectionFactory;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsConfiguration;
import org.apache.camel.spring.javaconfig.CamelConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.core.JmsTemplate;

import javax.inject.Singleton;
import javax.jms.Destination;
import java.util.List;

/**
 * Camel configuration, routes should be set set here
 *
 * @author wvergara, created on 5/18/15
 */
@ComponentScan(basePackages = { "com.wmv.poc.gator.integration.camel.routes"})
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

    @Bean
    public ActiveMQConnectionFactory jmsConnectionFactory(){
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL("tcp://localhost:61617");
        return connectionFactory;
    }

    @Bean
    public PooledConnectionFactory pooledConnectionFactory(){
        PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory();
        pooledConnectionFactory.setMaxConnections(8);
        pooledConnectionFactory.setConnectionFactory(jmsConnectionFactory());

        return pooledConnectionFactory;
    }

    @Bean
    public JmsConfiguration jmsConfig(){
        JmsConfiguration jmsConfiguration = new JmsConfiguration(pooledConnectionFactory());
        jmsConfiguration.setConcurrentConsumers(10);
        return jmsConfiguration;
    }
    @Bean
    public ActiveMQComponent activemq(){
        ActiveMQComponent activeMQComponent = new ActiveMQComponent();
        activeMQComponent.setConfiguration(jmsConfig());
        return activeMQComponent;
    }
    @Bean
    public JmsTemplate jmsTemplate(){
        JmsTemplate jmsTemplate = new JmsTemplate(pooledConnectionFactory());
        jmsTemplate.setDefaultDestination(new Destination() {

        });
        return  jmsTemplate;
    }



}