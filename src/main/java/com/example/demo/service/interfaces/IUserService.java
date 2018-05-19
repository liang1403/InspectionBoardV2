package com.example.demo.service.interfaces;

import com.example.demo.domain.User;
import com.example.demo.service.base.IEntityService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends IEntityService<User, String>, UserDetailsService {
}
