package com.example.demo.domain;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "application")
public class Application {

    @Setter(AccessLevel.NONE)
    @Id
    private String id;

    private Timestamp date;

    @ManyToOne
    private Enrollee enrollee;

    @ManyToOne
    private Speciality speciality;
}
