package edu.smu.cs5392.service;

import edu.smu.cs5392.model.Message;
import edu.smu.cs5392.repository.MessageRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing Message entities.
 */
@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    // Retrieve all messages
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    // Retrieve a message by ID
    public Optional<Message> getMessageById(String messageID) {
        return messageRepository.findById(messageID);
    }

    // Create or update a message
    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    // Delete a message by ID
    public void deleteMessageById(String messageID) {
        messageRepository.deleteById(messageID);
    }

    // Delete all messages
    public void deleteAllMessages() {
        messageRepository.deleteAll();
    }
}

