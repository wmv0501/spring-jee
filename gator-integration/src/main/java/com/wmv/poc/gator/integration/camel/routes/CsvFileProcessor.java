package com.wmv.poc.gator.integration.camel.routes;

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
        List<Map<String, Object>> modelMap = (List<Map<String, Object>>) in.getBody();
        Order order = (Order) modelMap.get(0).get(Order.class.getCanonicalName());
        log.debug(order.getFirstname());

        exchange.getOut().setBody(order);


    }
}
