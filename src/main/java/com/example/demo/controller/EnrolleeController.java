package com.example.demo.controller;

import com.example.demo.config.AuthenticationUtilities;
import com.example.demo.domain.Enrollee;
import com.example.demo.domain.ExamResult;
import com.example.demo.service.interfaces.IEnrolleeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/enrollee")
public class EnrolleeController extends EntityController<Enrollee> {

    @Autowired
    public EnrolleeController(IEnrolleeService enrolleeService) {
        super(enrolleeService);
    }

    @GetMapping("/list")
    public String getGridPage() {
        return "enrollee_grid";
    }

    @GetMapping("/panel")
    public String getPanel() {
        return "enrollee_panel";
    }
}
