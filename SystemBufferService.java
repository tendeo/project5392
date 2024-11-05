package edu.smu.cs5392.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.smu.cs5392.model.SystemBuffer;
import edu.smu.cs5392.repository.SystemBufferRepository;

import java.util.List;

@Service
public class SystemBufferService {

    @Autowired
    private SystemBufferRepository repository;

    public List<SystemBuffer> getAllBuffers() {
        return repository.findAll();
    }

    public SystemBuffer getBufferById(String messageID) {
        return repository.findById(messageID).orElse(null);
    }

    public SystemBuffer saveBuffer(SystemBuffer systemBuffer) {
        return repository.save(systemBuffer);
    }

    public void deleteBuffer(String messageID) {
        repository.deleteById(messageID);
    }
}
