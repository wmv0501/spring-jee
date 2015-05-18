package com.wmv.poc.swagger.controllers;

import com.wmv.poc.swagger.UserDetails;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wilson/greeting")
public class PersonController {
	@RequestMapping(value = "/{name}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
             })
	 public UserDetails sayHello(@PathVariable String name) {
	  String result="Hello "+name;
	  return new UserDetails("Wilson", "wilson.vergara");  
	 }  
	
	

}
