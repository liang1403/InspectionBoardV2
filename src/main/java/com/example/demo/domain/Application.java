package com.example.demo.domain;

import com.example.demo.config.AuthenticationUtilities;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@Table(name = "application")
@EqualsAndHashCode(callSuper = true)
public class Application extends Identified {

    @ManyToOne
    @JoinColumn(name = "enrolleeId", nullable = false)
    private Enrollee enrollee;

    @ManyToOne
    @JoinColumn(name = "specialityId", nullable = false)
    private Speciality speciality;

    @PrePersist
    public void setDefaults() {
        if(Objects.isNull(enrollee)) {
            this.setEnrollee(AuthenticationUtilities.getCurrentEnrollee());
        }
    }
}
