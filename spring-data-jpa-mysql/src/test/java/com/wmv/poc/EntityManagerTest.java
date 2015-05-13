package com.wmv.poc;

import com.wmv.poc.jpa.dao.UserDao;
import com.wmv.poc.jpa.entity.User;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import static  org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import javax.persistence.EntityManager;

/**
 * Created by wvergara on 5/12/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/application-context.xml"})
//@ContextConfiguration(classes = PersistenceContext.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class })
@Transactional
public class EntityManagerTest {
    @Autowired
    private EntityManager em;

    @Autowired
    private UserDao userDao;

    @Test
    public void testEmPersist(){
        final String ORIG_FIRSTNAME = "ORIGINAL_FNAME";
        final String UPDATED_FIRSTNAME = "UPDATED_FNAME";
        final String USERNAME = "PERSISTED_USER";
        User user = new User();
        user.setUsername(USERNAME);
        user.setFirstname(ORIG_FIRSTNAME);
        em.persist(user);
        User persistedUser = userDao.findUserByUsername(USERNAME);

        // first test
        assertThat(persistedUser.getFirstname(), equalTo(ORIG_FIRSTNAME));

        // Changes the fname value after 'persist' call.
        user.setFirstname(UPDATED_FIRSTNAME);

        User updatedUser = userDao.findUserByUsername(USERNAME);
        // second test. Expect that the value in database was changed
        assertThat(updatedUser.getFirstname(), equalTo(UPDATED_FIRSTNAME));
    }

    @Test
    public void testEmMerge(){
        final String ORIG_FIRSTNAME = "ORIGINAL_FNAME";
        final String UPDATED_FIRSTNAME = "UPDATED_FNAME";
        final String USERNAME = "PERSISTED_USER";
        User user = new User();
        user.setUsername(USERNAME);
        user.setFirstname(ORIG_FIRSTNAME);
        em.merge(user);
        User persistedUser = userDao.findUserByUsername(USERNAME);
        // first test
        assertThat(persistedUser.getFirstname(), equalTo(ORIG_FIRSTNAME));

        // Changes the fname value after 'persist' call.
        user.setFirstname(UPDATED_FIRSTNAME);

        User updatedUser = userDao.findUserByUsername(USERNAME);
        // second test. Expect that the value in database did not changes
        assertThat(updatedUser.getFirstname(), equalTo(ORIG_FIRSTNAME));
    }


}
