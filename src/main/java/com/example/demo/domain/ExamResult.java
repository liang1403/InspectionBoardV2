package com.example.demo.domain;

import com.example.demo.domain.listeners.ExamResultEntityListener;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(name = "examResult")
@EqualsAndHashCode(callSuper = true)
@EntityListeners(ExamResultEntityListener.class)
public class ExamResult extends Identified {

    @Column
    private Short mark;

    @ManyToOne
    @JoinColumn(name = "subjectId", nullable = false)
    private Subject subject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enrolleeId", nullable = false)
    private Enrollee enrollee;

    @ManyToOne
    @JoinColumn(name = "stateId", nullable = false)
    private ExamResultState state;
}
