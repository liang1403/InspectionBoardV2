package com.example.demo.controller;

import com.example.demo.config.AuthenticationUtilities;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.example.demo.config.Constants.DEFAULT_USER_VIEW;
import static com.example.demo.config.Constants.LOGIN_VIEW;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String getLoginPage() {
        if(AuthenticationUtilities.isCurrentUserAuthenticated()) {
            return "redirect:/" + DEFAULT_USER_VIEW;
        }
        return LOGIN_VIEW;
    }
}
