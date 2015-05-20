package com.wmv.poc.gator.integration.model.dto;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

import java.io.Serializable;

/**
 * @author wvergara, created on 5/18/15.
 */
@CsvRecord(separator = ",")
public class Order implements Serializable{

    @DataField(pos = 1)
    private String firstname;

    @DataField(pos = 2)
    private String middlename;

    @DataField(pos = 3)
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
