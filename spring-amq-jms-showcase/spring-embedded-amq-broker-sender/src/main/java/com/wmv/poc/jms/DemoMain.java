package com.wmv.poc.jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Queue;

/**
 * Created by wvergara on 5/4/15.
 */
public class DemoMain {
    final static String POC_DESTINATION_01 = "poc.dest_01";
    final static String POC_DESTINATION_02 = "poc.dest_02";
    final static String BROKER_URL = "tcp://localhost:61617";

    public static void main(String[] args) {

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.setDefaultDestinationName(POC_DESTINATION_01);

        // get bean from context
        JmsMessageSender jmsMessageSender = new JmsMessageSender(jmsTemplate);

        // send to default destination
        jmsMessageSender.send("hello JMS");

        // send to a code specified destination
        Queue queue = new ActiveMQQueue(POC_DESTINATION_02);
        jmsMessageSender.send(queue, "hello Another Message");

    }
}