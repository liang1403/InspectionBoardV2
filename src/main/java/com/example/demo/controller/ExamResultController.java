package com.example.demo.controller;

import com.example.demo.config.AuthenticationUtilities;
import com.example.demo.domain.Enrollee;
import com.example.demo.domain.ExamResult;
import com.example.demo.domain.ExamResultState;
import com.example.demo.domain.Subject;
import com.example.demo.service.interfaces.IExamResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/exam_result")
public class ExamResultController extends EntityController<ExamResult> {

    IExamResultService examResultService;

    @Autowired
    public ExamResultController(IExamResultService examResultService) {
        super(examResultService);
        this.examResultService = examResultService;
    }

    @Override
    @GetMapping("/entities")
    public @ResponseBody
    Page<ExamResult> list(GridPageRequest pageRequest) {
        Enrollee enrollee = AuthenticationUtilities.getCurrentEnrollee();
        if(Objects.nonNull(enrollee)) {
            return examResultService.findAllByEnrolleeId(enrollee.getId(), pageRequest);
        }
        return super.list(pageRequest);
    }

    @GetMapping("/states")
    public @ResponseBody
    List<ExamResultState> getExamResultStateList() {
        return examResultService.getExamResultStateList();
    }

    @GetMapping("/subjects")
    public @ResponseBody
    List<Subject> getSubjectList() {
        return examResultService.getSubjectList();
    }
}
