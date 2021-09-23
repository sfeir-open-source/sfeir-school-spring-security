package com.sfeir.demo5.security.user;

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
