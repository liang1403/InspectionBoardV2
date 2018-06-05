package com.example.demo.service;

import com.example.demo.domain.Identified;
import com.example.demo.domain.User;
import com.example.demo.domain.extension.UserDetailsExtended;
import com.example.demo.repository.EnrolleeDao;
import com.example.demo.repository.UserDao;
import com.example.demo.service.base.EntityServiceImpl;
import com.example.demo.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

import static com.example.demo.config.Constants.ROLE_USER_NAME;

@Service
public class UserServiceImpl extends EntityServiceImpl<User, String> implements IUserService {

    private final EnrolleeDao enrolleeDao;

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao repo, EnrolleeDao enrolleeDao) {
        super(repo);
        this.userDao = repo;
        this.enrolleeDao = enrolleeDao;
    }

    @Override
    public User create(User user) {
        boolean userExists = userDao.findByLogin(user.getLogin()) != null;
        if (!userExists) {
            super.create(user);
        }
        return user;
    }

    private Identified getConnectedEntity(User user, String roleName) {
        Identified connectedEntity = null;
        if (Objects.equals(roleName, ROLE_USER_NAME)) {
            connectedEntity = enrolleeDao.findByUserId(user.getId());
        }
        return connectedEntity;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid login");
        }
        String roleName = user.getRole().getName();
        Set<GrantedAuthority> roleAuthority = Collections.singleton(new SimpleGrantedAuthority(roleName));
        Identified connectedEntity = this.getConnectedEntity(user, roleName);
        return new UserDetailsExtended(user.getLogin(), user.getPassword(), roleAuthority, connectedEntity);
    }
}