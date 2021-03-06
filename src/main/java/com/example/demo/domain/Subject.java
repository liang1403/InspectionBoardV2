package com.example.demo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "subject")
@EqualsAndHashCode(callSuper=true)
public class Subject extends Identified {

    @Column
    private String name;
}
