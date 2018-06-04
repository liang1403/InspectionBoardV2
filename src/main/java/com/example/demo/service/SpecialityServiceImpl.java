package com.example.demo.service;

import com.example.demo.domain.Speciality;
import com.example.demo.repository.SpecialityDao;
import com.example.demo.service.base.EntityServiceImpl;
import com.example.demo.service.interfaces.ISpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialityServiceImpl extends EntityServiceImpl<Speciality, String> implements ISpecialityService {

    private final SpecialityDao specialityDao;

    @Autowired
    public SpecialityServiceImpl(SpecialityDao repo) {
        super(repo);
        this.specialityDao = repo;
    }

    public List<Speciality> findAllByEnrolleeId(String enrollee_id) {
        return specialityDao.findAllByEnrolleeId(enrollee_id);
    }
}