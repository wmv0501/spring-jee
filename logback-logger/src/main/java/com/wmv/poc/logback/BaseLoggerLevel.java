package com.wmv.poc.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wvergara on 5/7/15.
 */
public class BaseLoggerLevel<T extends  BaseLoggerLevel> {

    private Logger log ;

    public BaseLoggerLevel( Class<T> type) {
        log = LoggerFactory.getLogger(type);
    }

    public void log() {
        System.out.println("TRACE = " + (log.isTraceEnabled() ? "ENABLED" : "DISABLED"));
        System.out.println("DEBUG = " + (log.isDebugEnabled() ? "ENABLED" : "DISABLED"));
        System.out.println("INFO = " + (log.isInfoEnabled() ? "ENABLED" : "DISABLED"));
        System.out.println("WARN = " + (log.isWarnEnabled() ? "ENABLED" : "DISABLED"));
        System.out.println("ERROR = " + (log.isErrorEnabled() ? "ENABLED" : "DISABLED"));
        System.out.println();

    }
}
