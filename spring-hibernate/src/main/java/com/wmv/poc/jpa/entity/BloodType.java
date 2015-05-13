package com.wmv.poc.jpa.entity;

import javax.persistence.Embeddable;

/**
 * Created by wvergara on 5/11/15.
 */
//@Embeddable
public class BloodType {

    private String type;
    private String color;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
