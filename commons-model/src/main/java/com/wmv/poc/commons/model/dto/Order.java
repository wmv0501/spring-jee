package com.wmv.poc.commons.model.dto;

import java.io.Serializable;

/**
 *
 * @author wvergara, created on 5/21/15
 */
public class Order implements Serializable{

    private String firstname;

    private String middlename;

    private String lastname;


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
