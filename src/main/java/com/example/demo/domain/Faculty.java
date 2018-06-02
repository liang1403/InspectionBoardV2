package com.example.demo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "faculty")
@EqualsAndHashCode(callSuper=true)
public class Faculty extends Identified {

    @Column
    private String name;

    @OneToMany(mappedBy = "faculty", fetch = FetchType.LAZY)
    private Set<Speciality> specialities = new HashSet<>();
}
