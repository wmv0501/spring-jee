package com.wmv.poc.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wvergara on 5/7/15.
 *
 * Check configuration on logback.xml
 */
public class OffLoggerLevel extends BaseLoggerLevel<OffLoggerLevel>{

    private static final Logger log = LoggerFactory.getLogger(OffLoggerLevel.class);


    public OffLoggerLevel() {
        super(OffLoggerLevel.class);
    }
}
