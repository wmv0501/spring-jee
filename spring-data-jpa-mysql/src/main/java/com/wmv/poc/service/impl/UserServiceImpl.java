package com.wmv.poc.service.impl;

import com.wmv.poc.jpa.dao.GenericDao;
import com.wmv.poc.jpa.dao.GenericDaoImpl;
import com.wmv.poc.jpa.dao.UserDao;
import com.wmv.poc.jpa.entity.User;
import com.wmv.poc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by wvergara on 5/12/15.
 */
@Service
public class UserServiceImpl extends GenericServiceImpl<User, Long> implements UserService {

    @Autowired
    public UserServiceImpl( UserDao userDao) {
        super(userDao);
        }


    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @PostConstruct
    public void initIt() throws Exception {
        System.out.println("Init method after properties are set : " + message);
    }



}
