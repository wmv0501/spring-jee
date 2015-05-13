package com.wmv.poc.jpa.dao;

import com.wmv.poc.jpa.entity.User;
import org.springframework.stereotype.Component;

/**
 * Created by wvergara on 5/11/15.
 */
public interface UserDao extends GenericDao<User, Long> {
    User findUserByUsernameEm(String username);

    User findUserByUsernameSession(String username);

}
