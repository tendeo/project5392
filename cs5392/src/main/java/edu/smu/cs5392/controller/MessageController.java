package edu.smu.cs5392.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.smu.cs5392.model.Message;
import edu.smu.cs5392.service.MessageService;

import java.util.List;

@RestController
@RequestMapping("/api/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    // Add a new Message
    @PostMapping
    public ResponseEntity<Message> addMessage(@RequestBody Message message) {
        Message savedMessage = messageService.saveMessage(message);
        return ResponseEntity.ok(savedMessage);
    }

    // Get all Messages
    @GetMapping
    public ResponseEntity<List<Message>> getAllMessages() {
        List<Message> messages = messageService.getAllMessages();
        return ResponseEntity.ok(messages);
    }

    // Get Message by ID
    @GetMapping("/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable String id) {
        Message message = messageService.getMessageById(id);
        return ResponseEntity.ok(message);
    }

    // Update Message
    @PutMapping("/{id}")
    public ResponseEntity<Message> updateMessage(@PathVariable String id, @RequestBody Message updatedMessage) {
        Message message = messageService.updateMessage(id, updatedMessage);
        if (message != null) {
            return ResponseEntity.ok(message);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete Message by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable String id) {
        messageService.deleteMessage(id);
        return ResponseEntity.noContent().build();
    }
}

