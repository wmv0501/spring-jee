package org.springframework.security.samples.validation;

import org.springframework.security.samples.data.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.ConstraintViolation;
import javax.validation.metadata.BeanDescriptor;
import java.util.Set;

/**
 * @author wvergara, created on 6/10/15.
 */
@Component

public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        User user = (User) obj;
        if(user.getPassword().length()< 5){
            errors.rejectValue("password", "password.length",new Object[]{"Password"},"test");
        }

        if (user.getPassword().length()> 7) {
            throw new IllegalArgumentException("The supplied [Validator] is " +
                    "required and must not be null.");
        }

    }
}
