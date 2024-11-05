package edu.smu.cs5392.service;

import edu.smu.cs5392.repository.InboxRepository;
import edu.smu.cs5392.model.Inbox;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing Inbox entities.
 */
@Service
public class InboxService {

    private final InboxRepository inboxRepository;

    public InboxService(InboxRepository inboxRepository) {
        this.inboxRepository = inboxRepository;
    }

    // Retrieve all inbox entries
    public List<Inbox> getAllInboxes() {
        return inboxRepository.findAll();
    }

    // Retrieve by inboxID
    public List<Inbox> getByInboxID(String inboxID) {
        return inboxRepository.findByInboxID(inboxID);
    }
    
    // Retrieve an inbox entry by message ID
    public Optional<Inbox> getInboxById(String messageID) {
        return inboxRepository.findById(messageID);
    }

    // Create or update an inbox entry
    public Inbox saveInbox(Inbox inbox) {
        return inboxRepository.save(inbox);
    }

    // Delete an inbox entry by message ID
    public void deleteInboxById(String messageID) {
        inboxRepository.deleteById(messageID);
    }

    // Delete all inbox entries
    public void deleteAllInboxes() {
        inboxRepository.deleteAll();
    }
}
