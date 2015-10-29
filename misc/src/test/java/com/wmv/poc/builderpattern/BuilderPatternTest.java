package com.wmv.poc.builderpattern;

import java.util.Map;

/**
 * @author wvergara, created on 7/9/15.
 */
public class BuilderPatternTest {
    public static void main(String[] args) {
        SubClass.Builder builder = new SubClass.Builder();

        builder.build();

        SubClass.Builder sClass = new SubClass.Builder();
        sClass.calories(1).build();


    }
}
