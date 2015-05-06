package com.wmv.poc.jpa.repository.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.wmv.poc.jpa.repository.User;


public class UserJpaSpecification {
	  public static Specification<User> usernamefoobar() {
		  
		  return new Specification<User>() {

			@Override
			public Predicate toPredicate(Root<User> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
		        return cb.equal(root.get("username"), "foobar");
			}
		};
	  }
}
