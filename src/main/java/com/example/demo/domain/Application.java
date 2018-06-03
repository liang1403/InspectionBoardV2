package com.example.demo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "application")
@EqualsAndHashCode(callSuper=true)
public class Application extends Identified {

    @Column
    private Timestamp date;

    @ManyToOne
    @JoinColumn(name = "enrolleeId", nullable = false)
    private Enrollee enrollee;

    @ManyToOne
    @JoinColumn(name = "specialityId", nullable = false)
    private Speciality speciality;
}
