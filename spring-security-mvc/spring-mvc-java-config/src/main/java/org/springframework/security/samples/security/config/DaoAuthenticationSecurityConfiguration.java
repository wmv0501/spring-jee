package org.springframework.security.samples.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.samples.config.DaoAuthenticationSecurityConfig;
import org.springframework.security.samples.security.CustomUserDetailsService;

/**
 * @author wvergara, created on 6/10/15.
 */
@Configuration
@ComponentScan(basePackages = "org.springframework.security.samples.security")
public class DaoAuthenticationSecurityConfiguration extends DaoAuthenticationSecurityConfig {

    @Autowired
    CustomUserDetailsService userDetailsService;

    @Override
    protected UserDetailsService userDetailsService() {
        return userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/resources/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .and()
                .logout()
                .permitAll();
    }
}
