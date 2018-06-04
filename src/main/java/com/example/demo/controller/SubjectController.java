package com.example.demo.controller;

import com.example.demo.config.AuthenticationUtilities;
import com.example.demo.domain.Enrollee;
import com.example.demo.domain.Subject;
import com.example.demo.service.interfaces.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/subject")
public class SubjectController extends EntityController<Subject> {

    private final ISubjectService subjectService;

    @Autowired
    public SubjectController(ISubjectService subjectService) {
        super(subjectService);
        this.subjectService = subjectService;
    }

    @Override
    public @ResponseBody
    List<Subject> getList() {
        if (AuthenticationUtilities.isUserInRole("USER")) {
            Enrollee enrollee = AuthenticationUtilities.getCurrentEnrollee();
            if (Objects.nonNull(enrollee)) {
                return subjectService.findAllByEnrolleeId(enrollee.getId());
            }
        }
        return Collections.emptyList();
    }
}
