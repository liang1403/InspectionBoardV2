package com.example.demo.controller;

import com.example.demo.config.AuthenticationUtilities;
import com.example.demo.config.DataTypeUtilities;
import com.example.demo.domain.Application;
import com.example.demo.domain.Enrollee;
import com.example.demo.domain.Speciality;
import com.example.demo.domain.extension.GridPageRequest;
import com.example.demo.service.interfaces.IApplicationService;
import com.example.demo.service.interfaces.ISpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller
@RequestMapping("/application")
public class ApplicationController extends EntityController<Application> {

    private final ISpecialityService specialityService;

    private final IApplicationService applicationService;

    @Autowired
    public ApplicationController(IApplicationService applicationService, ISpecialityService specialityService) {
        super(applicationService);
        this.applicationService = applicationService;
        this.specialityService = specialityService;
    }

    @Override
    public @ResponseBody
    Page<Application> getPage(GridPageRequest pageRequest, final HttpServletRequest request) {
        if (AuthenticationUtilities.isUserInRole("USER")) {
            Enrollee enrollee = AuthenticationUtilities.getCurrentEnrollee();
            if (Objects.nonNull(enrollee)) {
                return applicationService.findAllByEnrolleeId(enrollee.getId(), pageRequest);
            }
        }
        if (AuthenticationUtilities.isUserInRole("ADMIN")) {
            String speciality_id = request.getParameter("speciality");
            if (DataTypeUtilities.isUUID(speciality_id)) {
                Speciality speciality = specialityService.get(speciality_id);
                if (Objects.nonNull(speciality)) {
                    return applicationService.findAllBySpecialityId(
                            speciality_id, speciality.getPlacesQuantity(), pageRequest);
                }
            }
            return super.getPage(pageRequest, request);
        }
        return Page.empty(pageRequest);
    }
}
