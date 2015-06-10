package com.wmv.poc.logback;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wvergara on 5/7/15.
 */

public class LoggerDemoTest {

    Logger log = LoggerFactory.getLogger(LoggerDemoTest.class);

    @Before
    public void before() {

    }

    @Test
    public void testLogLevels() {
        log.info("Starting test for logLevel.TRACE: ");
        BaseLoggerLevel logger = new TraceLoggerLevel();
        logger.log();

        log.info("Starting test for logLevel.DEBUG: ");
        logger = new DebugLoggerLevel();
        logger.log();

        log.info("Starting test for logLevel.INFO: ");
        logger = new InfoLoggerLevel();
        logger.log();

        log.info("Starting test for logLevel.WARN: ");
        logger = new WarnLoggerLevel();
        logger.log();

        log.info("Starting test for logLevel.ERROR: ");
        logger = new ErrorLoggerLevel();
        logger.log();

        log.info("Starting test for logLevel.ALL: ");
        logger = new OffLoggerLevel();
        logger.log();

    }

    @Test
    public void testLogParameterizing() {
        final String entry = "My Entry";
        log.debug("The new entry is {}.", entry);

    }

    @Test
    public void testLogMultipleParameterizing() {
        final Object[] entries = {"Entry1", "Entry1", "Entry1"};
        log.debug("The new entry is {}, {}, {}.", entries);

    }


}
