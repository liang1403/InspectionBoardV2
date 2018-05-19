package com.example.demo.service;

import com.example.demo.domain.Role;
import com.example.demo.repository.RoleDao;
import com.example.demo.service.base.EntityServiceImpl;
import com.example.demo.service.interfaces.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends EntityServiceImpl<Role, String> implements IRoleService {

    @Autowired
    public RoleServiceImpl(RoleDao repo) {
        super(repo);
    }

    public Role findByName(String roleName) {
        return ((RoleDao)repo).findByName(roleName);
    }
}