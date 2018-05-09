package com.example.demo.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;

@Data
@Entity
@Table(name = "role")
public class Role {

    @Setter(AccessLevel.NONE)
    @Id
    private String id;

    private String name;
}

