package com.wmv.poc.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

/**
 * Created by wvergara on 5/4/15.
 */
@Component("messageListener")
public class MessageListener implements javax.jms.MessageListener {

    private static final Logger LOG = LoggerFactory.getLogger(MessageListener.class);

    public void onMessage(Message message) {
        System.out.println("MessageListener ");
        try {
            TextMessage msg = (TextMessage) message;
            LOG.info("Consumed message: " + msg.getText());
        } catch (JMSException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}