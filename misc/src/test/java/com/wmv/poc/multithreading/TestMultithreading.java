package com.wmv.poc.multithreading;

/**
 * @author wvergara, created on 7/8/15.
 */
public class TestMultithreading {
    public static void main(String args[]) {

        RunnableDemo R1 = new RunnableDemo( "Thread-1");
        R1.start();

        RunnableDemo R2 = new RunnableDemo( "Thread-2");
        R2.start();
    }
}
