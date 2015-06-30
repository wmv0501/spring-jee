package com.wmv.poc.singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wvergara, created on 6/16/15.
 */
public class SingletonThreadSafeImpl {
    private static boolean firstThread = true;
    private static Logger logger = LoggerFactory.getLogger(SingletonThreadSafeImpl.class);
    private static int processcounter=0;

    public static final SingletonThreadSafeImpl INSTANCE = new SingletonThreadSafeImpl();
    protected SingletonThreadSafeImpl() {
        logger.info("Instantiating singleton");
        simulateRandomActivity();
    }

    public static void process() {
        try {
            processcounter++;
            logger.info("processing activing [{}]", processcounter);

            Thread.currentThread().sleep(5 * 1000); //millis to seconds
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }


    private static void simulateRandomActivity() {
        final int processTime = 5; //seconds
        try {
            if (firstThread) {
                firstThread = false;
                logger.info("Simulating activity for first thread for {} seconds...", processTime);
                // This nap should give the second thread enough time
                // to get by the first thread.
                Thread.currentThread().sleep(processTime * 1000); //millis to seconds
            }
        } catch (InterruptedException ex) {
            logger.warn("Sleep interrupted");
        }
        System.out.println("done here1");

    }
}
