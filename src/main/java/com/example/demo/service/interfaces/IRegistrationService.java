package com.example.demo.service.interfaces;

import com.example.demo.domain.User;
import org.springframework.stereotype.Component;


public interface IRegistrationService {
    User register(User user);
}