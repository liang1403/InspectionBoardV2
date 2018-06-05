package com.example.demo.domain;

import com.example.demo.domain.listeners.UserEntityListener;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
@EqualsAndHashCode(callSuper = true)
@EntityListeners(UserEntityListener.class)
public class User extends Identified {

    @Column(unique = true)
    private String login;

    @Column
    private String password;

    @ManyToOne
    @JoinColumn(name = "roleId")
    private Role role;
}
