package com.wmv.poc.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wvergara on 5/7/15.
 *
 * Check configuration on logback.xml or logback-test.xml (for test classes)
 */
public class DebugLoggerLevel extends BaseLoggerLevel<DebugLoggerLevel> {

    public DebugLoggerLevel() {
        super(DebugLoggerLevel.class);
    }
}
