package com.example.demo.controller;

import com.example.demo.domain.Enrollee;
import com.example.demo.domain.User;
import com.example.demo.service.interfaces.IEnrolleeService;
import com.example.demo.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private static final String DEFAULT_USER_VIEW = "enrollee/panel";
    private static final String REGISTRATION_VIEW = "registration";

    private final IUserService userService;

    private final IEnrolleeService enrolleeService;

    @Autowired
    public RegistrationController(IUserService userService, IEnrolleeService enrolleeService) {
        this.userService = userService;
        this.enrolleeService = enrolleeService;
    }

    @ModelAttribute("enrollee")
    public Enrollee getEmptyObject() {
        return new Enrollee();
    }

    @GetMapping
    public String getRedirectPage() {
        return REGISTRATION_VIEW;
    }

    @PostMapping
    public String register(@ModelAttribute("enrollee") Enrollee enrollee) {
        User user = enrollee.getUser();
        userService.create(user);
        if(user.getId() == null) {
            return REGISTRATION_VIEW;
        }
        enrolleeService.create(enrollee);
        if (enrollee.getId() == null) {
            return REGISTRATION_VIEW;
        }
        userService.loadUserByUsername(user.getLogin());
        return "redirect:/" + DEFAULT_USER_VIEW;
    }
}
