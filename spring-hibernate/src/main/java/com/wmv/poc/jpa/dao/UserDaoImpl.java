package com.wmv.poc.jpa.dao;

import com.wmv.poc.jpa.entity.User;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by wvergara on 5/11/15.
 */
@Transactional
@Component
public class UserDaoImpl extends  GenericDaoImpl<User, Long> implements UserDao {

    @Autowired
    private EntityManager em;

    @Override
    public User findUserByUsernameEm(String username) {
        return (User) this.em
                .createQuery(
                        "from User where username = ?")
                .setParameter(1, username).getSingleResult();
    }

    @Transactional
    @Override
    public User findUserByUsernameSession(String username) {
        Query query = currentSession().createQuery(
                "from User " +
                        "where username=:username");
        query.setParameter("username", username);
        return (User) query.uniqueResult();
    }



}
