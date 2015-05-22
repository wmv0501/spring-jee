package com.wmv.poc.gator.integration.camel.processor;

import com.wmv.poc.gator.integration.model.dto.Order;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * @author wvergara, created on 5/18/15.
 */
public class CsvFileProcessor implements Processor {
    private static final Logger log = LoggerFactory.getLogger(CsvFileProcessor.class);


    @Override
    public void process(Exchange exchange) throws Exception {

        Message in = exchange.getIn();
        Map<String, Object> objectMap =(Map<String, Object>) in.getBody();
        Order order = (Order) objectMap.get(Order.class.getCanonicalName());

        // TODO: Code here
        // Transformation / Mapping etc.

        exchange.getOut().setBody(order);


    }
}
