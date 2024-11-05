package edu.smu.cs5392.repository;

import edu.smu.cs5392.model.Inbox;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Inbox entity.
 */
@Repository
public interface InboxRepository extends JpaRepository<Inbox, String> {
    // CRUD operations by inboxID
    List<Inbox> findByInboxID(String inboxID);
}


