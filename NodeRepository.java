package edu.smu.cs5392.repository;

import edu.smu.cs5392.model.Node;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Node entity.
 */
@Repository
public interface NodeRepository extends JpaRepository<Node, String> {
}
