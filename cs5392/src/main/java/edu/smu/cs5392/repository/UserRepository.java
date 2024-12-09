package edu.smu.cs5392.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.smu.cs5392.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);
    void deleteByUserName(String userName);
}

