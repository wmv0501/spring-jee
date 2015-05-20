package com.wmv.poc.gator.integration.controllers;

import com.wmv.poc.gator.integration.UserDetails;
import com.wmv.poc.gator.integration.camel.routes.FileCsvRouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wilson/greeting")
public class PersonController {


    @Autowired
    private FileCsvRouteBuilder FileCs;

    @RequestMapping(value = "/{name}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserDetails sayHello(@PathVariable String name) {
        String result = "Hello " + name;

        return new UserDetails("Wilson", FileCs.getRouteCollection().toString());

    }


}
