package com.example.demo.controller;

import com.example.demo.config.AuthenticationUtilities;
import com.example.demo.domain.Enrollee;
import com.example.demo.domain.User;
import com.example.demo.service.interfaces.IEnrolleeService;
import com.example.demo.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.config.Constants.*;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IEnrolleeService enrolleeService;

    @ModelAttribute("enrollee")
    public Enrollee getEmptyObject() {
        return new Enrollee();
    }

    @GetMapping
    public String getRegistrationPage() {
        if(AuthenticationUtilities.isCurrentUserAuthenticated()) {
            return "redirect:/" + DEFAULT_USER_VIEW;
        }
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
        return "redirect:/" + ENROLLEE_VIEW + "/panel";
    }
}
