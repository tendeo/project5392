package edu.smu.cs5392.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.smu.cs5392.model.Message;
import edu.smu.cs5392.repository.MessageRepository;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message getMessageById(String id) {
        return messageRepository.findById(id).orElse(null);
    }

    public void deleteMessage(String id) {
        messageRepository.deleteById(id);
    }

    public Message updateMessage(String id, Message updatedMessage) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateMessage'");
    }
}

