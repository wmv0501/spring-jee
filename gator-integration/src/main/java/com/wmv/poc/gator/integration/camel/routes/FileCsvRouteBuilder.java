package com.wmv.poc.gator.integration.camel.routes;

import com.wmv.poc.gator.integration.camel.processor.CsvFileProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.spi.DataFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Route builder from csv file to java class(POJO).
 *
 * @author wvergara, created on 5/18/15
 */
@Component("routebuilderOne")
public class FileCsvRouteBuilder extends RouteBuilder {
    private static Logger log = LoggerFactory.getLogger(FileCsvRouteBuilder.class);

    private DataFormat bindy = new BindyCsvDataFormat("com.wmv.poc.gator.integration.model.dto");

    @Override
    public void configure() throws Exception {
        from("file://src/data/csv?noop=true")
                .unmarshal(bindy).log("*** BINDY!")
                .process(new CsvFileProcessor())
                .to("direct:orderJms");


        from("file://src/data/csv/list?noop=true")
                .unmarshal(bindy).split(body()).log("*** BINDY LIST!")
                .process(new CsvFileProcessor())
                .to("direct:orderJms");
    }
}