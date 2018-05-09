package com.example.demo.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(name = "faculty")
public class Faculty {

    @Setter(AccessLevel.NONE)
    @Id
    private String id;

    private String name;

    @OneToMany(mappedBy = "faculty")
    private Collection<Speciality> specialities;
}
