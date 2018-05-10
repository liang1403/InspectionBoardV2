package com.example.demo.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "faculty")
public class Faculty {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid2")
    private String id;

    @Column
    private String name;

    @OneToMany(mappedBy = "faculty", fetch = FetchType.LAZY)
    private Set<Speciality> specialities = new HashSet<>();
}
