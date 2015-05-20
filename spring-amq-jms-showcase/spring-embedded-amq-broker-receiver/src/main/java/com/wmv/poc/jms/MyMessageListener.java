package com.wmv.poc.jms;

import com.wmv.poc.gator.integration.model.dto.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.jms.*;
import javax.jms.MessageListener;

/**
 * Created by wvergara on 5/4/15.
 */
@Component("messageListenerExample")
public class MyMessageListener implements MessageListener {

    private static final Logger LOG = LoggerFactory.getLogger(MyMessageListener.class);

    public void onMessage(Message message) {
        try {
            LOG.info("Received message: id( " +message.getJMSMessageID()+")");
            ObjectMessage msg = (ObjectMessage) message;
            Order order =(Order) msg.getObject();
            LOG.info("message: " + order.getFirstname()
            );
        } catch (JMSException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}