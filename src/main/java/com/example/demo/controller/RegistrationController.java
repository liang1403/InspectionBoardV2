package com.example.demo.controller;

import com.example.demo.config.AuthenticationUtilities;
import com.example.demo.domain.User;
import com.example.demo.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.config.Constants.DEFAULT_USER_VIEW;
import static com.example.demo.config.Constants.LOGIN_VIEW;
import static com.example.demo.config.Constants.REGISTRATION_VIEW;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private IUserService userService;

    @ModelAttribute("user")
    public User getEmptyObject() {
        return new User();
    }

    @GetMapping
    public String getRegistrationPage() {
        if(AuthenticationUtilities.isCurrentUserAuthenticated()) {
            return "redirect:/" + DEFAULT_USER_VIEW;
        }
        return REGISTRATION_VIEW;
    }

    @PostMapping
    public String register(@ModelAttribute("user") User user) {
        userService.create(user);
        if(user.getId() == null) {
            return REGISTRATION_VIEW;
        }
        return "redirect:/" + LOGIN_VIEW;
    }
}
