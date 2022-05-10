package com.sfeir.solution3.data.repository;

import com.sfeir.solution3.data.entity.SchoolUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SchoolUserRepository extends JpaRepository<SchoolUser, Long> {

    Optional<SchoolUser> findByName(String name);
}
