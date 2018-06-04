package com.example.demo.controller;

import com.example.demo.domain.Enrollee;
import com.example.demo.service.interfaces.IEnrolleeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/enrollee")
public class EnrolleeController extends EntityController<Enrollee> {

    @Autowired
    public EnrolleeController(IEnrolleeService enrolleeService) {
        super(enrolleeService);
    }

    @GetMapping("/panel")
    public String getPanel() {
        return "enrollee_panel";
    }
}
