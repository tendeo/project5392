package edu.smu.cs5392.repository;

import edu.smu.cs5392.model.Message;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Message entity.
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, String> {
}

