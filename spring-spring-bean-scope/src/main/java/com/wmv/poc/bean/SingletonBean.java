package com.wmv.poc.bean;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.inject.Singleton;

/**
 * Created by wvergara on 5/12/15.
 */
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@Component
public class SingletonBean {

    public static final String INITIAL_MESSAGE = "INITIAL MESSAGE";

    private String message = INITIAL_MESSAGE;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
