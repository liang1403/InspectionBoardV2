package com.example.demo.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;

@Data
@Entity
@Table(name = "exam_result")
public class ExamResult {

    @Setter(AccessLevel.NONE)
    @Id
    private String id;

    private short mark;

    @ManyToOne
    private Subject subject;

    @ManyToOne
    private Enrollee enrollee;
}
