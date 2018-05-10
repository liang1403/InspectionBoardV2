package com.example.demo.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name = "speciality")
public class Speciality {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

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
