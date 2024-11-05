package edu.smu.cs5392.repository;

import edu.smu.cs5392.model.NetworkEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for NetworkEntity.
 */
@Repository
public interface NetworkRepository extends JpaRepository<NetworkEntity, String> {
}
