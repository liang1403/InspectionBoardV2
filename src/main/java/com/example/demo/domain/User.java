package com.example.demo.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
public class User {

    @Setter(AccessLevel.NONE)
    @Id
    private String id;

    private String login;

    private String password;

    @ManyToOne
    private Role role;
}
