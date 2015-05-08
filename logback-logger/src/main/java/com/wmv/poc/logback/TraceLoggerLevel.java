package com.wmv.poc.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wvergara on 5/7/15.
 *
 * Check configuration on logback.xml
 */
public class TraceLoggerLevel  extends BaseLoggerLevel<TraceLoggerLevel>{

    private static final Logger log = LoggerFactory.getLogger(TraceLoggerLevel.class);


    public TraceLoggerLevel() {
        super(TraceLoggerLevel.class);
    }
}
