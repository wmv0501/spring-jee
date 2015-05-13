package com.wmv.poc.bean;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by wvergara on 5/12/15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/application-context.xml"})
//@ContextConfiguration(classes = PersistenceContext.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class })
public class PrototypeScopeBeanTest {

    @Autowired
    private PrototypeBean singletonBean;

    @Autowired
    private PrototypeBean singletonBean2;

    @Test
    public void test(){
        final String MESSAGE = "THIS_MESSAGE";
        final String SESSION2_MESSAGE = "SESSONBEAN_2_MESSAGE";
        assertThat(singletonBean.getMessage(), equalTo(PrototypeBean.INITIAL_MESSAGE) );
        assertThat(singletonBean2.getMessage(), equalTo(PrototypeBean.INITIAL_MESSAGE) );
        singletonBean2.setMessage(SESSION2_MESSAGE);
        assertThat(singletonBean2.getMessage(), equalTo(SESSION2_MESSAGE));

        singletonBean.setMessage(MESSAGE);

        assertThat(singletonBean.getMessage(), equalTo(MESSAGE));
        assertThat(singletonBean2.getMessage(), equalTo(SESSION2_MESSAGE));


    }


}
