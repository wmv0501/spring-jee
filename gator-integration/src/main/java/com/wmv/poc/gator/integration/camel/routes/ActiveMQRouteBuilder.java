package com.wmv.poc.gator.integration.camel.routes;

import org.apache.camel.builder.RouteBuilder;

/**
 * Created by wvergara on 5/5/15.
 */
public class ActiveMQRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("file:src/data?noop=true").to("activemq:poc.dest_02").log("rounting via jms.");
    }
}
