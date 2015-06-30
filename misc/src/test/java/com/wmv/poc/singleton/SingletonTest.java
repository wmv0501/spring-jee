package com.wmv.poc.singleton;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wvergara, created on 6/16/15.
 */
public class SingletonTest {
    private ClassicSingleton singletonInstance1 = null, singletonInstance2 = null;
    private static Logger logger = LoggerFactory.getLogger(SingletonTest.class);

    @Before
    public void setUp() {
//  Get first instance
        logger.info("getting first singleton instance...");
        singletonInstance1 = ClassicSingleton.getInstance();
        logger.info("1st singleton: " + singletonInstance1);

//  Get seconde instance
        logger.info("getting secord singleton instance...");
        singletonInstance2 = ClassicSingleton.getInstance();
        logger.info("2nd singleton: " + singletonInstance2);
    }

    @Test
      public void testUnique() {
        logger.info("Must be equal");
        Assert.assertEquals(true, singletonInstance1 == singletonInstance2);
    }


}

