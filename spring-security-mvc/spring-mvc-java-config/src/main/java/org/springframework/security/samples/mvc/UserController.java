package org.springframework.security.samples.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.samples.data.Message;
import org.springframework.security.samples.data.Timelog;
import org.springframework.security.samples.data.User;
import org.springframework.security.samples.data.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author wvergara, created on 6/10/15.
 */
@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    @Qualifier("userValidator")
    private Validator validator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }


    @Autowired
    private UserRepository userRepository;

    @Transactional
    @RequestMapping
    public ModelAndView list() {
        Iterable<User> users = userRepository.findAll();

        User newUser = new User();
        newUser.setName("wilson1");
        newUser.setPassword("password");
        newUser.setEmail("v@v.com");
        final Timelog timelog = new Timelog();
        timelog.setInTime(new Date());
        timelog.setOutTime(new Date());

        Set<Timelog> timelogs = new HashSet<Timelog>() {{
            add(timelog);
        }};

        newUser.setTimelogs(timelogs);

        User createdUser = userRepository.save(newUser);

        Timelog createTimeLog = createdUser.getTimelogs().iterator().next();

        return new ModelAndView("users/list", "users", users);
    }

    @RequestMapping("{id}")
    public ModelAndView view(@PathVariable("id") User user) {
        return new ModelAndView("users/show", "user", user);
    }


    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String createForm(@ModelAttribute User user) {
        return "users/compose";
    }


    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView create(@Valid User user, BindingResult result,
                               RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return new ModelAndView("users/compose");
        }
        user = userRepository.save(user);
        redirect.addFlashAttribute("globalMessage", "Successfully created a new User");
        return new ModelAndView("redirect:/users/{user.id}", "user.id", user.getId());
    }
}
