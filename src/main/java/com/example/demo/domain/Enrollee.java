package com.example.demo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(name = "enrollee")
@EqualsAndHashCode(callSuper=true)
public class Enrollee extends Identified {

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

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "userId", nullable = false)
    private User user;
}
