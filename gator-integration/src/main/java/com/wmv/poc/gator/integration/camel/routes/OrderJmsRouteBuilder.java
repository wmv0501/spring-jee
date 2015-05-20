package com.wmv.poc.gator.integration.camel.routes;

import com.wmv.poc.gator.integration.model.dto.Order;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author wvergara, created on 5/19/15.
 */
@Component
public class OrderJmsRouteBuilder extends AbstractJmsRouteBuilder<Order> {

    private final String DEFAULT_DESTINATION = "poc.dest_01";

    @Override
    public void configure() throws Exception {
        from("direct:orderJms").process(new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
                final Message in = exchange.getIn();
                Order order = (Order) in.getBody();
                send(order);
            }
        });
    }

    @Override
    String getDestinationName() {
        return DEFAULT_DESTINATION;
    }
}
