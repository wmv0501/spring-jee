package com.wmv.poc.jpa.dao;

import com.wmv.poc.jpa.entity.User;

/**
 * Created by wvergara on 5/11/15.
 */
public interface UserDao extends GenericDao<User, Long> {
    User findUserByUsername(String username);
}
