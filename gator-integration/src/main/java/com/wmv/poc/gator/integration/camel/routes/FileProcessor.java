package com.wmv.poc.gator.integration.camel.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wvergara on 5/3/15.
 */
public class FileProcessor implements Processor {

    private static final Logger log = LoggerFactory.getLogger(FileProcessor.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        log.info("Processing...{}", exchange.getIn().getBody());

    }
}
