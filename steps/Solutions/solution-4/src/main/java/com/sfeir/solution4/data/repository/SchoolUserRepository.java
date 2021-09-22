package com.sfeir.solution4.data.repository;

import com.sfeir.solution4.data.entity.SchoolUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolUserRepository extends JpaRepository<SchoolUser, Long> {
}
