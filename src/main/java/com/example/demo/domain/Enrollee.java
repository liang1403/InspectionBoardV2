package com.example.demo.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "enrollee")
public class Enrollee {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String surname;

    @Column
    private String name;

    @Column
    private String lastname;

    @Column
    private String address;

    @Column
    private String phone;

    @Column
    private short certificateAverageMark;

    @OneToMany(mappedBy = "enrollee", fetch = FetchType.LAZY)
    private List<ExamResult> examResults = new ArrayList<>();
}
