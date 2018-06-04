package com.example.demo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "faculty")
@EqualsAndHashCode(callSuper=true)
public class Faculty extends Identified {

    @Column
    private String name;
}
