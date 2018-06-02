package com.example.demo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(name = "exam_result")
@EqualsAndHashCode(callSuper=true)
public class ExamResult extends Identified {

    @Column
    private short mark;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "enrollee_id", nullable = false)
    private Enrollee enrollee;

    @ManyToOne
    @JoinColumn(name = "state_id", nullable = false)
    private ExamResultState state;
}
