package com.example.demo.service.interfaces;

import com.example.demo.domain.Subject;
import com.example.demo.service.base.IEntityService;

import java.util.List;

public interface ISubjectService extends IEntityService<Subject, String> {

    List<Subject> findAllByEnrolleeId(String enrollee_id);
}
