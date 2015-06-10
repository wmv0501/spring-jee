package com.wmv.poc.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wvergara on 5/7/15.
 *
 * Check configuration on logback.xml
 */
public class ErrorLoggerLevel extends BaseLoggerLevel<ErrorLoggerLevel>{

    private static final Logger log = LoggerFactory.getLogger(ErrorLoggerLevel.class);


    public ErrorLoggerLevel() {
        super(ErrorLoggerLevel.class);
    }
}
