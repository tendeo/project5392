package edu.smu.cs5392.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.smu.cs5392.model.SystemBuffer;

@Repository
@Transactional
public interface SystemBufferRepository extends JpaRepository<SystemBuffer, String> {
    // Custom query methods can be defined here if needed
}
