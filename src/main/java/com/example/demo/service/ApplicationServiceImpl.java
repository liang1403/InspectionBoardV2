package com.example.demo.service;

import com.example.demo.config.AuthenticationUtilities;
import com.example.demo.domain.Application;
import com.example.demo.domain.Enrollee;
import com.example.demo.domain.Speciality;
import com.example.demo.repository.ApplicationDao;
import com.example.demo.repository.SpecialityDao;
import com.example.demo.service.base.EntityServiceImpl;
import com.example.demo.service.interfaces.IApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ApplicationServiceImpl extends EntityServiceImpl<Application, String> implements IApplicationService {

    @Autowired
    private SpecialityDao specialityDao;

    private ApplicationDao applicationDao;

    @Autowired
    public ApplicationServiceImpl(ApplicationDao repo) {
        super(repo);
        this.applicationDao = repo;
    }

    @Override
    public Application create(Application application) {
        this.setDefaultOwner(application);
        return super.create(application);
    }

    private void setDefaultOwner(Application application) {
        Enrollee enrollee = AuthenticationUtilities.getCurrentEnrollee();
        if (Objects.isNull(enrollee)) {

        }
        application.setEnrollee(enrollee);
    }

    public Page<Application> findAllByEnrolleeId(String enrollee_id, Pageable pageable) {
        return applicationDao.findAllByEnrolleeId(enrollee_id, pageable);
    }

    public List<Speciality> getSpecialityList(String enrollee_id) {
        return specialityDao.findAllByEnrolleeId(enrollee_id);
    }
}