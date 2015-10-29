package com.wmv.poc.builderpattern;

public class SubClass extends SuperClass {

    private final boolean hasGMO;
    private final String str1="";

    public static class Builder extends SuperClass.Builder<Builder> {

        private boolean hasGMO = false;

        public Builder() {}

        public Builder GMO(boolean val) {
            hasGMO = val;
            return this;
        }

        public SubClass build() {
            return new SubClass(this);
        }
    }

    protected SubClass(Builder builder) {
        super(builder);
        hasGMO = builder.hasGMO;
    }



}