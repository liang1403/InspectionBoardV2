package com.example.demo.service;

import com.example.demo.domain.Identified;
import com.example.demo.domain.User;
import com.example.demo.domain.extension.UserDetailsExtended;
import com.example.demo.repository.EnrolleeDao;
import com.example.demo.repository.RoleDao;
import com.example.demo.repository.UserDao;
import com.example.demo.service.base.EntityServiceImpl;
import com.example.demo.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

@Service
public class UserServiceImpl extends EntityServiceImpl<User, String> implements IUserService {

    private final PasswordEncoder passwordEncoder;

    private final RoleDao roleDao;

    private final EnrolleeDao enrolleeDao;

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao repo, PasswordEncoder passwordEncoder, RoleDao roleDao, EnrolleeDao enrolleeDao) {
        super(repo);
        this.userDao = repo;
        this.passwordEncoder = passwordEncoder;
        this.roleDao = roleDao;
        this.enrolleeDao = enrolleeDao;
    }

    @Override
    public User create(User user) {
        boolean userExists = userDao.findByLogin(user.getLogin()) != null;
        if (!userExists) {
            this.setDefaultRole(user);
            this.encodePassword(user);
            super.create(user);
        }
        return user;
    }

    private void encodePassword(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }

    private void setDefaultRole(User user) {
        if (Objects.isNull(user.getRole())) {
            user.setRole(roleDao.findByName("ROLE_USER"));
        }
    }

    private Identified getConnectedEntity(User user, String roleName) {
        Identified connectedEntity = null;
        switch (roleName) {
            case "ROLE_USER":
                connectedEntity = enrolleeDao.findByUserId(user.getId());
                break;
            default:
                break;
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