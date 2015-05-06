package com.wmv.poc.routes;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;

/**
 * Created by wvergara on 5/5/15.
 */
public class ActiveMQRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("activemq:queue:poc.dest_01").log(LoggingLevel.INFO, "received1").to("mock:result");
    }
}
