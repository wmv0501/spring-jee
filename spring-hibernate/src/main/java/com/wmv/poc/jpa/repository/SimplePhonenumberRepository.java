package com.wmv.poc.jpa.repository;

import com.wmv.poc.jpa.entity.Phonenumber;
import com.wmv.poc.jpa.entity.QPhonenumber;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by wvergara on 5/11/15.
 */
public interface SimplePhonenumberRepository  extends CrudRepository<Phonenumber, Long>{

}
