package com.example.demo.service;

import com.example.demo.domain.Application;
import com.example.demo.repository.ApplicationDao;
import com.example.demo.service.base.EntityServiceImpl;
import com.example.demo.service.interfaces.IApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationServiceImpl extends EntityServiceImpl<Application, Integer> implements IApplicationService {

    @Autowired
    public ApplicationServiceImpl(ApplicationDao repo) {
        super(repo);
    }
}