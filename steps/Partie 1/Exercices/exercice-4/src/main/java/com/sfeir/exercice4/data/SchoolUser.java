package com.sfeir.exercice4.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import javax.persistence.*;

@Data
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
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
