package edu.smu.cs5392.controller;

import edu.smu.cs5392.service.MessageService;
import edu.smu.cs5392.model.Message;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST Controller for managing Message entities.
 */
@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    // CREATE or UPDATE a message
    @PostMapping
    public ResponseEntity<Message> createOrUpdateMessage(@RequestBody Message message) {
        try {
            Message savedMessage = messageService.saveMessage(message);
            return new ResponseEntity<>(savedMessage, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // READ: Get all messages
    @GetMapping
    public ResponseEntity<List<Message>> getAllMessages() {
        List<Message> messages = messageService.getAllMessages();
        if (messages.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    // READ: Get a message by ID
    @GetMapping("/{messageID}")
    public ResponseEntity<Message> getMessageById(@PathVariable String messageID) {
        Optional<Message> message = messageService.getMessageById(messageID);
        return message.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // DELETE: Delete a message by ID
    @DeleteMapping("/{messageID}")
    public ResponseEntity<HttpStatus> deleteMessageById(@PathVariable String messageID) {
        try {
            messageService.deleteMessageById(messageID);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE: Delete all messages
    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllMessages() {
        try {
            messageService.deleteAllMessages();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

