package com.wmv.poc.gator.integration.camel.routes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wvergara on 5/1/15.
 */
public class MyFileRouteBuilder extends SimpleFileRouteBuilder {
    Logger log = LoggerFactory.getLogger(MyFileRouteBuilder.class);

    @Override
    public void configure() throws Exception {
        log.info("Adding process in simple file rounte builder");
        super.configure();
        getRouteDefinition().log("*** Adding from simplefileRouteBulder");
    }
}
