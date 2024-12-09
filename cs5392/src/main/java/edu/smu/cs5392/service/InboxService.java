package edu.smu.cs5392.service;

import org.springframework.stereotype.Service;

import edu.smu.cs5392.model.Inbox;
import edu.smu.cs5392.repository.InboxRepository;
import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class InboxService {

    @Autowired
    private InboxRepository inboxRepository;

    // Save an Inbox entity
    public Inbox saveInbox(Inbox inbox) {
        return inboxRepository.save(inbox);
    }

    // Retrieve all Inbox entities
    public List<Inbox> getAllInboxes() {
        return inboxRepository.findAll();
    }

    // Retrieve a specific Inbox entity by ID
    public Optional<Inbox> getInboxById(Long id) {
        return inboxRepository.findById(id);
    }

    // Delete an Inbox entity by ID
    public void deleteInbox(Long id) {
        inboxRepository.deleteById(id);
    }

    // Update an Inbox entity
    public Inbox updateInbox(Long id, Inbox updatedInbox) {
        Optional<Inbox> existingInbox = inboxRepository.findById(id);
        if (existingInbox.isPresent()) {
            Inbox inbox = existingInbox.get();
            inbox.setInboxID(updatedInbox.getInboxID());
            inbox.setMessageID(updatedInbox.getMessageID());
            inbox.setTimeReceived(updatedInbox.getTimeReceived());
            inbox.setOwnerNodeID(updatedInbox.getOwnerNodeID());
            inbox.setInboxSize(updatedInbox.getInboxSize());
            return inboxRepository.save(inbox);
        }
        return null;
    }
}



