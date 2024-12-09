package edu.smu.cs5392.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.smu.cs5392.model.Node;

public interface NodeRepository extends JpaRepository<Node, String> {
}
