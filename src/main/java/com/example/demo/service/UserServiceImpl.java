package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.repository.UserDao;
import com.example.demo.service.base.EntityServiceImpl;
import com.example.demo.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends EntityServiceImpl<User, Integer> implements IUserService {

    @Autowired
    public UserServiceImpl(UserDao repo) {
        super(repo);
    }
}