package com.wmv.poc.mapper;

import com.wmv.poc.jpa.entity.User;
import com.wmv.poc.jpa.entity.dto.UserDto;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

/**
 * Created by wvergara on 5/8/15.
 */
@Component
public class Mapper extends ConfigurableMapper {
    protected void configure(MapperFactory factory) {
        factory.classMap(User.class, UserDto.class)
                .field("firstname", "givenname")
                .field("lastname", "surname")
                .byDefault()
                .register();


    }
}