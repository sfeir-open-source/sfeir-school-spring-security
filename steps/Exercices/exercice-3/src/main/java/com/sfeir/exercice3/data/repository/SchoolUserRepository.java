package com.sfeir.exercice3.data.repository;

import com.sfeir.exercice3.data.entity.SchoolUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SchoolUserRepository extends JpaRepository<SchoolUser, Long> {

    Optional<SchoolUser> findByName(String name);
}
