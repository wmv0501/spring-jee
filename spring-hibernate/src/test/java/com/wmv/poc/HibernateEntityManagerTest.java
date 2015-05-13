package com.wmv.poc;

import com.google.common.collect.ImmutableList;
import com.wmv.poc.jpa.dao.UserDao;
import com.wmv.poc.jpa.entity.Phonenumber;
import com.wmv.poc.jpa.entity.User;
import com.wmv.poc.jpa.repository.SimplePhonenumberRepository;
import com.wmv.poc.jpa.repository.SimpleUserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;


/**
 * Created by wvergara on 5/4/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/application-context.xml"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class })

@Transactional
public class HibernateEntityManagerTest {

    Logger log = LoggerFactory.getLogger(HibernateEntityManagerTest.class);

    @Autowired
    private SimpleUserRepository simpleUserRepository;

    @Autowired
    private SimplePhonenumberRepository phonenumberRepository;

    @Autowired
    private UserDao userDao;

    @Before
    public void before(){
        User user = new User();
        user.setFirstname("Firstname_test1");
        user.setUsername("TestUsername1");
        user.setLastname("Lastname_test1");
        user.setPhonenumber(ImmutableList.of(new Phonenumber("12344"), new Phonenumber("12355")));
        simpleUserRepository.save(user);
    }


    @Test
    public void testUserDaoUsingEm(){
        User user = userDao.findUserByUsernameEm("TestUsername1");
        assertThat(user, notNullValue());

    }


}
