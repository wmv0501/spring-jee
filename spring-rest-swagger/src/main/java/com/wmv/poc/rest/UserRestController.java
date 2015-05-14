package com.wmv.poc.rest;

import com.wmv.poc.jpa.entity.User;
import com.wmv.poc.jpa.entity.dto.UserDto;
import com.wmv.poc.jpa.repository.SimpleUserRepository;
import com.wmv.poc.mapper.Mapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wvergara on 5/8/15.
 */
@RestController
@RequestMapping(value="user")

public class UserRestController {

    @Autowired
    private SimpleUserRepository repository;

    @Autowired
    Mapper mapper;

    @Autowired
    DozerBeanMapper dozerBeanMapper;

    @RequestMapping(method= RequestMethod.GET,value="dozer", produces = { MediaType.APPLICATION_JSON_VALUE})
    public UserDto greeting(@RequestParam(value="name", defaultValue="World") String name) {

            return dozerBeanMapper.map(repository.findOne(1l), UserDto.class);
    }

    @RequestMapping(method= RequestMethod.GET,value="orika", produces = { MediaType.APPLICATION_JSON_VALUE})
    public UserDto getUserDTOOrika(@RequestParam(value="name", defaultValue="World") String name) {
        return mapper.map(repository.findOne(1l), UserDto.class);
    }
}
