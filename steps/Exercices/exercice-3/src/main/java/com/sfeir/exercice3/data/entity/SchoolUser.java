package com.sfeir.exercice3.data.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class SchoolUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String role;

    private String password;

    private boolean enabled;
}
