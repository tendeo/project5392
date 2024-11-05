package edu.smu.cs5392.controller;

import edu.smu.cs5392.service.InboxService;
import edu.smu.cs5392.model.Inbox;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST Controller for managing Inbox entities.
 */
@RestController
@RequestMapping("/api/inboxes")
public class InboxController {

    private final InboxService inboxService;

    public InboxController(InboxService inboxService) {
        this.inboxService = inboxService;
    }

    // CREATE or UPDATE an inbox entry
    @PostMapping
    public ResponseEntity<Inbox> createOrUpdateInbox(@RequestBody Inbox inbox) {
        try {
            Inbox savedInbox = inboxService.saveInbox(inbox);
            return new ResponseEntity<>(savedInbox, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // READ: Get all inbox entries
    @GetMapping
    public ResponseEntity<List<Inbox>> getAllInboxes() {
        List<Inbox> inboxes = inboxService.getAllInboxes();
        if (inboxes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(inboxes, HttpStatus.OK);
    }

    // READ: Get all inbox entry by inbox ID
    // Get entries by inboxID
    @GetMapping("/inbox/{inboxID}")
    public ResponseEntity<List<Inbox>> getByInboxID(@PathVariable String inboxID) {
        List<Inbox> entries = inboxService.getByInboxID(inboxID);
        if (entries.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(entries, HttpStatus.OK);
    }

    // READ: Get an inbox entry by message ID
    @GetMapping("/{messageID}")
    public ResponseEntity<Inbox> getInboxById(@PathVariable String messageID) {
        Optional<Inbox> inbox = inboxService.getInboxById(messageID);
        return inbox.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // DELETE: Delete an inbox entry by message ID
    @DeleteMapping("/{messageID}")
    public ResponseEntity<HttpStatus> deleteInboxById(@PathVariable String messageID) {
        try {
            inboxService.deleteInboxById(messageID);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE: Delete all inbox entries
    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllInboxes() {
        try {
            inboxService.deleteAllInboxes();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

