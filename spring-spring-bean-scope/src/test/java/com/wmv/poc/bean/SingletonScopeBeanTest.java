package com.wmv.poc.bean;

import com.wmv.poc.bean.SingletonBean;
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

/**
 * Created by wvergara on 5/12/15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/application-context.xml"})
//@ContextConfiguration(classes = PersistenceContext.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class })
public class SingletonScopeBeanTest {

    @Autowired
    private SingletonBean singletonBean;

    @Autowired
    private SingletonBean singletonBean2;

    @Test
    public void test(){
        final String MESSAGE = "THIS_MESSAGE";
        assertThat(singletonBean.getMessage(), equalTo(SingletonBean.INITIAL_MESSAGE) );
        assertThat(singletonBean2.getMessage(), equalTo(SingletonBean.INITIAL_MESSAGE) );
        singletonBean2.setMessage("SESSONBEAN_2_MESSAGE");
        singletonBean.setMessage(MESSAGE);

        assertThat(singletonBean.getMessage(), equalTo(MESSAGE));
        assertThat(singletonBean2.getMessage(), equalTo(MESSAGE));

        assertEquals(singletonBean2.getMessage(), MESSAGE);

    }


}
