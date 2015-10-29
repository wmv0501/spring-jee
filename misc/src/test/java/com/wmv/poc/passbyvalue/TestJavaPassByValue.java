package com.wmv.poc.passbyvalue;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author wvergara, created on 7/7/15.
 */
public class TestJavaPassByValue {
    @Test
    public  void testPassByValue() {
        Dog tashee = new Dog("Dugal");
        Dog newDog = foo(tashee);

        Assert.assertEquals("Max",tashee.getName());


    }

    public Dog foo(Dog someDog) {
        someDog.setName("Max");     // AAA
        someDog = new Dog("Fifi");  // BBB
        someDog.setName("Rowlf");   // CCC
        return someDog;
    }
    class Dog{

        public Dog(String name) {
            this.name = name;
        }

        String name;

        public String getName() {
            return name;
        }

        public Dog setName(String name) {
            this.name = name;
            return this;
        }
    }

}
