package com.example.demo.controller;

import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.service.interfaces.IRegistrationService;
import com.example.demo.service.interfaces.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final IRegistrationService registrationService;
    private final IRoleService roleService;

    @Autowired
    public RegistrationController(IRegistrationService registrationService, IRoleService roleService) {
        this.registrationService = registrationService;
        this.roleService = roleService;
    }

    @ModelAttribute("user")
    public User getClientObject() {
        return new User();
    }

    @GetMapping
    public ModelAndView toRegistrationPage() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(!(auth instanceof AnonymousAuthenticationToken)){
            return new ModelAndView("redirect:/login");
        }
        return new ModelAndView("registration");
    }


    @PostMapping
    public String register(@ModelAttribute("user") User user) {
        user.setRole(roleService.get(1));
        User registered = registrationService.register(user);
        return registered == null ? "registration" : "login";
    }
}
