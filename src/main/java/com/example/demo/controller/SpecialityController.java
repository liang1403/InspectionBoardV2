package com.example.demo.controller;

import com.example.demo.config.AuthenticationUtilities;
import com.example.demo.domain.Enrollee;
import com.example.demo.domain.Speciality;
import com.example.demo.service.interfaces.ISpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/speciality")
public class SpecialityController extends EntityController<Speciality> {

    private final ISpecialityService specialityService;

    @Autowired
    public SpecialityController(ISpecialityService specialityService) {
        super(specialityService);
        this.specialityService = specialityService;
    }

    @Override
    public @ResponseBody
    List<Speciality> getList() {
        List<Speciality> specialities = new ArrayList<>();
        specialities.add(new Speciality());

        if (AuthenticationUtilities.isUserInRole("USER")) {
            Enrollee enrollee = AuthenticationUtilities.getCurrentEnrollee();
            if (Objects.nonNull(enrollee)) {
                specialities.addAll(specialityService.findAllByEnrolleeId(enrollee.getId()));
            }
        }
        if (AuthenticationUtilities.isUserInRole("ADMIN")) {
            specialities.addAll(super.getList());
        }

        return specialities;
    }
}
