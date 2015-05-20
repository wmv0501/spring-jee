package com.wmv.poc.gator.integration;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
* Created by wvergara on 5/18/15.
*/
@XmlRootElement
public class UserDetails {
    private String userName;
    private String emailId;

    public UserDetails() {

    }

    public UserDetails(String userName, String emailId) {
        super();
        this.userName = userName;
        this.emailId = emailId;
    }

    @XmlAttribute
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @XmlAttribute
    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}

