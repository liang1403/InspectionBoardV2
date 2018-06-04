package com.example.demo.service.interfaces;

import com.example.demo.domain.Speciality;
import com.example.demo.service.base.IEntityService;

import java.util.List;

public interface ISpecialityService extends IEntityService<Speciality, String> {

    List<Speciality> findAllByEnrolleeId(String enrollee_id);
}
