package com.example.demo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "role")
@EqualsAndHashCode(callSuper=true)
public class Role extends Identified {

    @Column(unique = true)
    private String name;
}

