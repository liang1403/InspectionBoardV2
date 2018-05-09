package com.example.demo.service;

import com.example.demo.domain.Enrollee;
import com.example.demo.repository.EnrolleeDao;
import com.example.demo.service.base.EntityServiceImpl;
import com.example.demo.service.interfaces.IEnrolleeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrolleeServiceImpl extends EntityServiceImpl<Enrollee, String> implements IEnrolleeService {

    @Autowired
    public EnrolleeServiceImpl(EnrolleeDao repo) {
        super(repo);
    }
}
