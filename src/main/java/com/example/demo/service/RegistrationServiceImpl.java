package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.repository.UserDao;
import com.example.demo.service.interfaces.IRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;


@Component
public class RegistrationServiceImpl implements IRegistrationService {
    private final UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationServiceImpl(UserDao userDao){
        this.userDao = userDao;
    }

    public User register(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDao.save(user);
    }
}
