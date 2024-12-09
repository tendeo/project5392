package edu.smu.cs5392.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.smu.cs5392.model.Inbox;

import org.springframework.stereotype.Repository;

@Repository
public interface InboxRepository extends JpaRepository<Inbox, Long> {

    // Find all messages in a specific inbox
    List<Inbox> findByInboxID(String inboxID);

    // Find a specific message by InboxID and MessageID
    Inbox findByInboxIDAndMessageID(String inboxID, String messageID);
}