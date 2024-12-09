package edu.smu.cs5392.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.smu.cs5392.model.Inbox;
import edu.smu.cs5392.service.InboxService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inbox")
public class InboxController {

    @Autowired
    private InboxService inboxService;

    // Add a new Inbox
    @PostMapping
    public ResponseEntity<Inbox> addInbox(@RequestBody Inbox inbox) {
        Inbox savedInbox = inboxService.saveInbox(inbox);
        return ResponseEntity.ok(savedInbox);
    }

    // Get all Inboxes
    @GetMapping
    public ResponseEntity<List<Inbox>> getAllInboxes() {
        List<Inbox> inboxes = inboxService.getAllInboxes();
        return ResponseEntity.ok(inboxes);
    }

    // Get Inbox by ID
    @GetMapping("/{id}")
    public ResponseEntity<Inbox> getInboxById(@PathVariable Long id) {
        Optional<Inbox> inbox = inboxService.getInboxById(id);
        return inbox.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update Inbox
    @PutMapping("/{id}")
    public ResponseEntity<Inbox> updateInbox(@PathVariable Long id, @RequestBody Inbox updatedInbox) {
        Inbox inbox = inboxService.updateInbox(id, updatedInbox);
        if (inbox != null) {
            return ResponseEntity.ok(inbox);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete Inbox by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInbox(@PathVariable Long id) {
        inboxService.deleteInbox(id);
        return ResponseEntity.noContent().build();
    }
}

