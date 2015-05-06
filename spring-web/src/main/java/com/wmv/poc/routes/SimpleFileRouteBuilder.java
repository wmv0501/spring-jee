package com.wmv.poc.routes;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.model.RoutesDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by wvergara on 5/4/15.
 */

public abstract class SimpleFileRouteBuilder extends RouteBuilder {
    private static final Logger log = LoggerFactory.getLogger(SimpleFileRouteBuilder.class);

    private String fileSource;
    private String fileDestination;
    private boolean idempotent = Boolean.TRUE;
    private boolean autoStartup = Boolean.TRUE;
    private boolean autoCreateDir = Boolean.TRUE;


    public boolean isIdempotent() {
        return idempotent;
    }

    public void setIdempotent(boolean idempotent) {
        this.idempotent = idempotent;
    }

    public boolean isAutoStartup() {
        return autoStartup;
    }

    public void setAutoStartup(boolean autoStartup) {
        this.autoStartup = autoStartup;
    }

    public boolean isAutoCreateDir() {
        return autoCreateDir;
    }

    public void setAutoCreateDir(boolean autoCreateDir) {
        this.autoCreateDir = autoCreateDir;
    }

    public String getFileSource() {

        return fileSource;
    }

    public void setFileSource(String fileSource) {
        this.fileSource = fileSource;
    }

    public String getFileDestination() {
        return fileDestination;
    }

    public void setFileDestination(String fileDestination) {
        this.fileDestination = fileDestination;
    }

    @Override
    public void configure() throws Exception {
        log.info("configuring..." + getFileSource());
        final String FILE_SOURCE="file:" + getFileSource() ;
        from(FILE_SOURCE)
            .process(new FileProcessor()).autoStartup(isAutoStartup())
            .to(getFileDestination()).log(LoggingLevel.INFO, "Routing file: ${header.CamelFileName} to " + getFileDestination());
    }

    protected RouteDefinition getRouteDefinition() throws Exception {
        RoutesDefinition routeCollection= super.getRouteCollection();
        return routeCollection.getRoutes().get(0);

    }


}
