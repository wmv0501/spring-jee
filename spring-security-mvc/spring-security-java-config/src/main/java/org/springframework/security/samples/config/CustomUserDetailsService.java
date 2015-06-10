package org.springframework.security.samples.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.samples.data.User;
import org.springframework.security.samples.service.UserService;
import org.springframework.stereotype.Component;

/**
 * @author wvergara, created on 6/9/15.
 */
@Component
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName)
            throws UsernameNotFoundException {
        User user = userService.findUserByEmail(userName);
        if(user == null){
            throw new UsernameNotFoundException("UserName "+userName+" not found");
        }

        return new SecurityUser(user);
    }

}