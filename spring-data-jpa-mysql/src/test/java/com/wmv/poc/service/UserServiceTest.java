package com.wmv.poc.service;

import com.google.common.collect.ImmutableList;
import com.wmv.poc.jpa.entity.Phonenumber;
import com.wmv.poc.jpa.entity.User;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
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

import java.util.List;

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
public class UserServiceTest  {

    @Autowired
    UserService userService;

    @Test
    public void testSaveOrUpdate() {
        List<User> users = userService.getAll();
        assertThat(users, hasSize(2));
        User user = new User();
        user.setFirstname("Firstname_test1");
        user.setUsername("TestUsername1");
        user.setLastname("Lastname_test1");
        assertThat(user.getId(), nullValue());
        User createdUser = userService.saveOrUpdate(user);
        assertThat(createdUser.getId(), notNullValue());

    }

    @Test
    public void testGetAll() {
        List<User> users = userService.getAll();
        assertThat(users, hasSize(2));

    }

    @Test
    public void testGet() {
        User user =  userService.get(2l);
        assertThat(user, notNullValue());
    }

    @Test
    public void testUpdate() {

    }

    @Test
    public void testRemove() {

    }
}
