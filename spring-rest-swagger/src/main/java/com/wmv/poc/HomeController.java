package com.wmv.poc;

import com.wmv.poc.jpa.entity.QUser;
import com.wmv.poc.jpa.entity.User;
import com.wmv.poc.jpa.repository.AdvanceUserRepository;
import com.wmv.poc.jpa.repository.SimpleUserRepository;
import com.wmv.poc.jpa.repository.UserRepositoryCustom;
import com.wmv.poc.jpa.repository.specification.UserJpaSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@Autowired
	SimpleUserRepository repository;
	
	@Autowired
	UserRepositoryCustom repositoryCustom;
	
	@Autowired
	AdvanceUserRepository repositoryAdvance;
	
	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		User user;

		QUser user2 = QUser.user;
		user = new User();
		user.setUsername("foobar");
		user.setFirstname("firstname");
		user.setLastname("lastname");
		user = repository.save(user);


		logger.info("repository.count(); {}.",
				repository.findOne(user2.username.eq("foobar")));
		logger.info("UserRepositoryCustom.findByHQL; {}.",
				repositoryCustom.findByHQL());
		logger.info("UserRepositoryCustom.findByCriteria; {}.",
				repositoryCustom.findByCriteria());
		logger.info("UserRepositoryAdvance.usernamefoobar; {}.",
				repositoryAdvance.findAll(UserJpaSpecification.usernamefoobar()));
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

}
