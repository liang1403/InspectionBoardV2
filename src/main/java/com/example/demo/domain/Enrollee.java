package com.example.demo.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(name = "enrollee")
public class Enrollee {

    @Setter(AccessLevel.NONE)
    @Id
    private String id;

    private String surname;

    private String name;

    private String lastname;

    private String address;

    private String phone;

    private short certificateAverageMark;

    @OneToMany(mappedBy = "enrollee")
    private Collection<ExamResult> examResults;
}
