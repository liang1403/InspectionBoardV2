package com.example.demo.controller;

import com.example.demo.config.AuthenticationUtilities;
import com.example.demo.domain.Application;
import com.example.demo.domain.Enrollee;
import com.example.demo.domain.Speciality;
import com.example.demo.service.interfaces.IApplicationService;
import com.example.demo.service.interfaces.IExamResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/application")
public class ApplicationController extends EntityController<Application> {

    @Autowired
    IExamResultService examResultService;

    IApplicationService applicationService;

    @Autowired
    public ApplicationController(IApplicationService applicationService) {
        super(applicationService);
        this.applicationService = applicationService;
    }

    @Override
    @GetMapping("/entities")
    public @ResponseBody
    Page<Application> list(GridPageRequest pageRequest) {
        Enrollee enrollee = AuthenticationUtilities.getCurrentEnrollee();
        if(Objects.nonNull(enrollee)) {
            return applicationService.findAllByEnrolleeId(enrollee.getId(), pageRequest);
        }
        return super.list(pageRequest);
    }

    @GetMapping("/specialities")
    public @ResponseBody
    List<Speciality> getSpecialitiesList() {
        Enrollee enrollee = AuthenticationUtilities.getCurrentEnrollee();
        if(Objects.nonNull(enrollee)) {
            return applicationService.getSpecialityList(enrollee.getId());
        }
        return Collections.emptyList();
    }
}
