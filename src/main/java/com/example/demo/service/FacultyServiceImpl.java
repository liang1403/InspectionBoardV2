package com.example.demo.service;

import com.example.demo.domain.Faculty;
import com.example.demo.repository.FacultyDao;
import com.example.demo.service.base.EntityServiceImpl;
import com.example.demo.service.interfaces.IFacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacultyServiceImpl extends EntityServiceImpl<Faculty, String> implements IFacultyService {

    @Autowired
    public FacultyServiceImpl(FacultyDao repo) {
        super(repo);
    }
}