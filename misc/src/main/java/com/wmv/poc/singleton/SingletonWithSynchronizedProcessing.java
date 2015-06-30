package com.wmv.poc.singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wvergara, created on 6/16/15.
 *         This implementation of Singleton is thread-safe using synchonized block.
 */
public class SingletonWithSynchronizedProcessing {
    private static SingletonWithSynchronizedProcessing instance = null;
    private static boolean firstThread = true;
    private static SingletonWithSynchronizedProcessing singleton = null;
    private static Logger logger = LoggerFactory.getLogger(SingletonWithSynchronizedProcessing.class);

    protected SingletonWithSynchronizedProcessing() {
        // Exists only to defeat instantiation.
    }

    public static SingletonWithSynchronizedProcessing getInstance() {
        // Double-checked locking
        if (instance == null) {
            // other thread will wait to finish processing activity inside the synchronized block
            synchronized (SingletonWithSynchronizedProcessing.class) {
                if (instance == null) {


                    if (!firstThread)
                        logger.warn("Creating new instance where there should only be one, this proves that this implementation of " +
                                "Singleton is NOT thread-safe");

            /* this makes a delay to create an instance, instance object is still null at this point.
            * so it the next thread called the getInstance and saw that instance object is null, it will
            * enter here and create another instance. Non thread-safe*/
                    simulateRandomActivity();

                    instance = new SingletonWithSynchronizedProcessing();
                    logger.info("Created new instance of {}", instance);

                }
            }

        }

        return instance;
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
    }
}
