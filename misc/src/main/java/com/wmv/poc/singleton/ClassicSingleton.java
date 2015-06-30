package com.wmv.poc.singleton;

/**
 * @author wvergara, created on 6/16/15.
 */
public class ClassicSingleton {
    private static ClassicSingleton instance = null;
    protected ClassicSingleton() {
        // Exists only to defeat instantiation.
    }
    public static ClassicSingleton getInstance() {
        if(instance == null) {
            instance = new ClassicSingleton();
        }
        return instance;
    }
}
