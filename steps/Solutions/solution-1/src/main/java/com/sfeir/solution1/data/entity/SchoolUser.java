package com.sfeir.solution1.data.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class SchoolUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String password;

    private String role;

    private boolean enabled = true;
}
