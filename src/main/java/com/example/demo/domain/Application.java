package com.example.demo.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "application")
public class Application {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid2")
    private String id;

    @Column
    private Timestamp date;

    @ManyToOne
    @JoinColumn(name = "enrollee_id", nullable = false)
    private Enrollee enrollee;

    @ManyToOne
    @JoinColumn(name = "speciality_id", nullable = false)
    private Speciality speciality;
}
