package com.wmv.poc.gator.integration.camel.routes;

import com.wmv.poc.gator.integration.model.dto.Order;
import com.wmv.poc.gator.integration.util.OrderMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wvergara, created on 5/19/15.
 */
@Component
public class OrderJmsRouteBuilder extends AbstractJmsRouteBuilder<Order> {
    private static final Logger log = LoggerFactory.getLogger(OrderJmsRouteBuilder.class);

    private final String DEFAULT_DESTINATION = "poc.dest_01";

    @Autowired
    OrderMapper orderMapper;

    @Override
    public void configure() throws Exception {
        from("direct:orderJms").process(new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
                final Message in = exchange.getIn();
                Order order = (Order) in.getBody();
                com.wmv.poc.commons.model.dto.Order orderDto = orderMapper.map(order, com.wmv.poc.commons.model.dto.Order.class);
                send(orderDto);
            }
        });
    }

    @Override
    protected String getDestinationName() {
        return DEFAULT_DESTINATION;
    }
}
