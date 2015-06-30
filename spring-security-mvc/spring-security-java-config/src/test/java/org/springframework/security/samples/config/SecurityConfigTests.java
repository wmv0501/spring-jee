/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.security.samples.config;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContextTestExecutionListener;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.ServletTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.testSecurityContext;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//import org.springframework.security.samples.mvc.config.WebMvcConfiguration;

/**
 * @author Rob Winch
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = { RootConfiguration.class, WebMvcConfiguration.class })
@WebAppConfiguration
@TestExecutionListeners(listeners = {ServletTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        WithSecurityContextTestExecutionListener.class})
public class SecurityConfigTests {
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private Filter springSecurityFilterChain;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilters(springSecurityFilterChain)
                .defaultRequest(get("/").with(testSecurityContext())).build();
    }

    @Test
    public void requestProtectedResourceRequiresAuthentication() throws Exception {
        mvc.perform(get("/")).andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    public void loginSuccess() throws Exception {
        mvc.perform(formLogin()).andExpect(redirectedUrl("/"));
    }

    @Test
    public void loginFailure() throws Exception {
        mvc.perform(formLogin().password("invalid")).andExpect(
                redirectedUrl("/login?error"));
    }

    @Test
    @WithMockUser
    public void requestProtectedResourceWithUser() throws Exception {
        mvc.perform(get("/")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void composeMessageRequiresCsrfToken() throws Exception {
        MockHttpServletRequestBuilder composeMessage = post("/").param("summary",
                "New Message").param("text", "This is a new message");

        mvc.perform(composeMessage).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser
    public void composeMessage() throws Exception {
        MockHttpServletRequestBuilder composeMessage = post("/")
                .param("summary", "New Message").param("text", "This is a new message")
                .with(csrf());

        mvc.perform(composeMessage).andExpect(redirectedUrlPattern("/*"));
    }

    @Test
    @WithMockUser
    public void logoutRequiresCsrfToken() throws Exception {
        mvc.perform(post("/logout")).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser
    public void logoutSuccess() throws Exception {
        mvc.perform(logout()).andExpect(redirectedUrl("/login?logout"))
                .andExpect(unauthenticated());
    }
}
