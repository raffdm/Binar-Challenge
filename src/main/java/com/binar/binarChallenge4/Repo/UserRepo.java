package com.binar.binarChallenge4.Repo;

import com.binar.binarChallenge4.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, String> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<User> findByUsername(String username);

    @Query("SELECT p FROM User p WHERE LOWER(p.username) LIKE LOWER(CONCAT('%', :nama, '%'))")
    Optional<User> findUser(@Param("nama") String nama);
}
