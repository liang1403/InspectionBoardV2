package com.example.demo.domain;

import com.example.demo.config.AuthenticationUtilities;
import com.example.demo.domain.listeners.ApplicationEntityListener;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

@Data
@Entity
@Table(name = "application")
@EqualsAndHashCode(callSuper = true)
@EntityListeners(ApplicationEntityListener.class)
public class Application extends Identified {

    @ManyToOne
    @JoinColumn(name = "enrolleeId", nullable = false)
    private Enrollee enrollee;

    @ManyToOne
    @JoinColumn(name = "specialityId", nullable = false)
    private Speciality speciality;

    @Column
    private Double point;
}
