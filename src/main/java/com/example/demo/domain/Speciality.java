package com.example.demo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name = "speciality")
@EqualsAndHashCode(callSuper = true)
public class Speciality extends Identified {

    @Column
    private String name;

    @Column
    private Short placesQuantity;

    @Column
    private BigDecimal certificateWeight;

    @ManyToOne
    @JoinColumn(name = "facultyId", nullable = false)
    private Faculty faculty;

    @ManyToMany
    @JoinTable(
            name = "subject_in_speciality",
            joinColumns = @JoinColumn(name = "speciality_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private List<Subject> subjects;
}
