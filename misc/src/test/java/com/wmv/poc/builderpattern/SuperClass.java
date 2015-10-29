package com.wmv.poc.builderpattern;

import java.util.Map;

public class SuperClass {

    private final int calories;

    public static class Builder<T extends Builder> {
        protected Map<String, Class<?>> classVar;

        private int calories = 0;

        public Builder() {}

        public T calories(int val) {
            calories = val;
            return (T) this;
        }

        public SuperClass build() { return new SuperClass(this); }
    }

    protected SuperClass(Builder builder) {
        calories = builder.calories;
    }
}