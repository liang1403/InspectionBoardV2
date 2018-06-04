package com.example.demo.domain;

import com.example.demo.config.AuthenticationUtilities;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@Table(name = "examResult")
@EqualsAndHashCode(callSuper = true)
public class ExamResult extends Identified {

    @Column
    private Short mark;

    @ManyToOne
    @JoinColumn(name = "subjectId", nullable = false)
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "enrolleeId", nullable = false)
    private Enrollee enrollee;

    @ManyToOne
    @JoinColumn(name = "stateId", nullable = false)
    private ExamResultState state;

    @PrePersist
    public void setDefaults() {
        if(Objects.isNull(enrollee)) {
            this.setEnrollee(AuthenticationUtilities.getCurrentEnrollee());
        }
    }
}
