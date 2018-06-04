package com.example.demo.controller;

import com.example.demo.config.AuthenticationUtilities;
import com.example.demo.domain.Enrollee;
import com.example.demo.domain.ExamResult;
import com.example.demo.domain.ExamResultState;
import com.example.demo.domain.Subject;
import com.example.demo.domain.extension.GridPageRequest;
import com.example.demo.service.interfaces.IExamResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/exam_result")
public class ExamResultController extends EntityController<ExamResult> {

    private final IExamResultService examResultService;

    @Autowired
    public ExamResultController(IExamResultService examResultService) {
        super(examResultService);
        this.examResultService = examResultService;
    }

    @Override
    public @ResponseBody
    Page<ExamResult> getPage(GridPageRequest pageRequest, final HttpServletRequest request) {
        if (AuthenticationUtilities.isUserInRole("USER")) {
            Enrollee enrollee = AuthenticationUtilities.getCurrentEnrollee();
            if (Objects.nonNull(enrollee)) {
                return examResultService.findAllByEnrolleeId(enrollee.getId(), pageRequest);
            }
        }
        if (AuthenticationUtilities.isUserInRole("ADMIN")) {
            return super.getPage(pageRequest, request);
        }
        return Page.empty(pageRequest);
    }

    @GetMapping("/states")
    public @ResponseBody
    List<ExamResultState> getExamResultStateList() {
        return examResultService.findAllExamResultStates();
    }
}
