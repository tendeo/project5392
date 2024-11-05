package edu.smu.cs5392.repository;

import edu.smu.cs5392.model.LoginCredentials;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for LoginCredentials entity.
 */
@Repository
public interface LoginCredentialsRepository extends JpaRepository<LoginCredentials, String> {

    // Find by username
    Optional<LoginCredentials> findByUserName(String userName);
}
