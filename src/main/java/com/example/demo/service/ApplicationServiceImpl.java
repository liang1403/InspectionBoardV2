package com.example.demo.service;

import com.example.demo.domain.Application;
import com.example.demo.repository.ApplicationDao;
import com.example.demo.service.base.EntityServiceImpl;
import com.example.demo.service.interfaces.IApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ApplicationServiceImpl extends EntityServiceImpl<Application, String> implements IApplicationService {

    private final ApplicationDao applicationDao;

    @Autowired
    public ApplicationServiceImpl(ApplicationDao repo) {
        super(repo);
        this.applicationDao = repo;
    }

    public Page<Application> findAllByEnrolleeId(String enrollee_id, Pageable pageable) {
        return applicationDao.findAllByEnrolleeId(enrollee_id, pageable);
    }

    public Page<Application> findAllBySpecialityIdOrderByPoint(String speciality_id, Short limit, Pageable pageable) {
        return applicationDao.findAllBySpecialityIdOrderByPoint(speciality_id, limit, pageable);
    }
}