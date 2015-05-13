package com.wmv.poc.jpa.dao;

import com.wmv.poc.jpa.entity.User;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

/**
 * Created by wvergara on 5/11/15.
 */
@Transactional
@Component
public class UserDaoImpl extends  GenericDaoImpl<User, Long> implements UserDao {

    @Override
    public User findUserByUsername(String username) {
        return (User) getEm()
                .createQuery(
                        "from User where username = :username")
                .setParameter("username", username).getSingleResult();
    }

}
