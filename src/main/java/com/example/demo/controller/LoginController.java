package com.example.demo.controller;

import com.example.demo.config.AuthenticationUtilities;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    static final String DEFAULT_USER_VIEW = "enrollee/panel";
    static final String DEFAULT_ADMIN_VIEW = "admin/panel";
    static final String LOGIN_VIEW = "login";
    static final String REGISTRATION_VIEW = "registration";

    @GetMapping
    public String getLoginPage() {
        if(AuthenticationUtilities.isUserInRole("USER")) {
            return "redirect:/" + DEFAULT_USER_VIEW;
        }
        if(AuthenticationUtilities.isUserInRole("ADMIN")) {
            return "redirect:/" + DEFAULT_ADMIN_VIEW;
        }
        return LOGIN_VIEW;
    }
}
