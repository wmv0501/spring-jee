package com.wmv.poc.singleton;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


/**
 * @author wvergara, created on 6/16/15.
 */
public class SingletonThreadSafeImplTest {
    private static SingletonThreadSafeImpl singleton = null;
    private static Logger logger = LoggerFactory.getLogger(SingletonTest.class);
    @Before
    public void setup(){
        singleton = null;
    }
    @Test
    public void testUnique() throws InterruptedException {
        // Both threads call Singleton.getInstance().
        Thread threadOne = new Thread(new SingletonTestRunnable()),
                threadTwo = new Thread(new SingletonTestRunnable());
        threadOne.start();
        threadTwo.start();
        threadOne.join();
        threadTwo.join();
    }



    @Test
    public void testUnique2() throws InterruptedException {
        // Both threads call Singleton.getInstance().
        Thread threadOne = new Thread(new SingletonTestRunnable()),
                threadTwo = new Thread(new SingletonTestRunnable());
        threadOne.start();
        threadTwo.start();
        threadOne.join();
        threadTwo.join();
    }

    private static class SingletonTestRunnable implements Runnable {
        public void run() {
            // Get a reference to the singleton.
            SingletonThreadSafeImpl s = SingletonThreadSafeImpl.INSTANCE;
            s.process();
            // Protect singleton member variable from
            // multithreaded access.
            synchronized(SingletonTest.class) {
                if(singleton == null) // If local reference is null...
                    singleton = s;     // ...set it to the singleton
            }


            // Local reference must be equal to the one and
            // only instance of Singleton; otherwise, we have two
            // Singleton instances.
            logger.info("Instance {} must be equal to intance {}, result: {}", s,s,s == singleton);

            assertEquals(true, s == singleton);
        }
    }
}
