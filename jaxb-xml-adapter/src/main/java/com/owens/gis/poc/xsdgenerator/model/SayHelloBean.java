package com.owens.gis.poc.xsdgenerator.model;

/**
 * @author wvergara, created on 7/2/15.
 */
public class SayHelloBean {

        private static final String HELLO_MSG = "Hello ";
        public String sayHelloTo(String name){
            return HELLO_MSG + name;
        }
    }

