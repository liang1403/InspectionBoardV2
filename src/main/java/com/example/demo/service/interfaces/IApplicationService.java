package com.example.demo.service.interfaces;

import com.example.demo.domain.Application;
import com.example.demo.domain.Speciality;
import com.example.demo.domain.Subject;
import com.example.demo.service.base.IEntityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IApplicationService extends IEntityService<Application, String> {

    Page<Application> findAllByEnrolleeId(String enrollee_id, Pageable pageable);

    public List<Speciality> getSpecialityList(String enrollee_id);
}