package com.example.demo.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name = "speciality")
public class Speciality {

    @Setter(AccessLevel.NONE)
    @Id
    private String id;

    private String name;

    private short placesQuantity;

    private BigDecimal certificateWeight;

    @ManyToOne
    private Faculty faculty;

    @OneToMany
    @JoinTable(
            name = "subject_in_speciality",
            joinColumns = @JoinColumn(name = "speciality_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private List<Subject> subjects;
}
