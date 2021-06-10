package com.miniproject.userservice.repository;

import com.miniproject.userservice.dto.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersDetailRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByUsername(String name);

    Optional<Users> findByEmail(String email);

    @Query(value = "SELECT SEQ_USER.nextval FROM DUAL", nativeQuery = true)
    Integer getNextSeriesId();
}
