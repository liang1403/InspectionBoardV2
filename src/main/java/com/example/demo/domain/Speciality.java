package com.example.demo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "speciality")
@EqualsAndHashCode(callSuper=true)
public class Speciality extends Identified {

    @Column
    private String name;

    @Column
    private short placesQuantity;

    @Column
    private BigDecimal certificateWeight;

    @ManyToOne
    @JoinColumn(name = "faculty_id", nullable = false)
    private Faculty faculty;

//    @OneToMany
//    @JoinTable(
//            name = "subject_in_speciality",
//            joinColumns = @JoinColumn(name = "speciality_id"),
//            inverseJoinColumns = @JoinColumn(name = "subject_id")
//    )
//    private List<Subject> subjects;
}
