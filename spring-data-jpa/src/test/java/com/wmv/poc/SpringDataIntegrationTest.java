package com.wmv.poc;

import com.wmv.poc.jpa.entity.User;
import com.wmv.poc.jpa.repository.SimpleUserRepository;
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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


/**
 * Created by wvergara on 5/4/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/application-context.xml"})
//@ContextConfiguration(classes = PersistenceContext.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class })
public class SpringDataIntegrationTest {

    Logger log = LoggerFactory.getLogger(SpringDataIntegrationTest.class);

    @Autowired
    private SimpleUserRepository simpleUserRepository;

    @Test
    public void TestFindByFirstname(){
        User user = simpleUserRepository.findOne(1L);
        assertThat( user.getFirstname(), equalTo("adminFamily"));

    }
}
