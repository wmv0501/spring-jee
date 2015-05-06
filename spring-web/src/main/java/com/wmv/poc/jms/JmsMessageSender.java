package com.wmv.poc.jms;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created by wvergara on 5/4/15.
 */
public class JmsMessageSender {

    private JmsTemplate jmsTemplate;

    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    /**
     * send text to default destination
     *
     * @param text
     */
    public void send(final String text) {

        this.jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                Message message = session.createTextMessage(text);
                return message;
            }
        });
    }

    /**
     * Simplify the send by using convertAndSend
     *
     * @param text
     */
    public void sendText(final String text) {
        this.jmsTemplate.convertAndSend(text);
    }

    /**
     * Send text message to a specified destination
     *
     * @param text
     */
    public void send(final javax.jms.Destination dest, final String text) {

        this.jmsTemplate.send(dest, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                Message message = session.createTextMessage(text);
                return message;
            }
        });
    }
}
