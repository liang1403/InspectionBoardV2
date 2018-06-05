package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

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
    private Short certificateAverageMark;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "userId", nullable = false)
    private User user;
}
