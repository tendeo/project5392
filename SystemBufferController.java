package edu.smu.cs5392.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.smu.cs5392.model.SystemBuffer;
import edu.smu.cs5392.service.SystemBufferService;

import java.util.List;

@RestController
@RequestMapping("/api/system-buffer")
public class SystemBufferController {

    @Autowired
    private SystemBufferService service;

    @GetMapping
    public List<SystemBuffer> getAllBuffers() {
        return service.getAllBuffers();
    }

    @GetMapping("/{messageID}")
    public ResponseEntity<SystemBuffer> getBufferById(@PathVariable String messageID) {
        SystemBuffer buffer = service.getBufferById(messageID);
        return buffer != null ? ResponseEntity.ok(buffer) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public SystemBuffer createBuffer(@RequestBody SystemBuffer systemBuffer) {
        return service.saveBuffer(systemBuffer);
    }

    @DeleteMapping("/{messageID}")
    public ResponseEntity<Void> deleteBuffer(@PathVariable String messageID) {
        service.deleteBuffer(messageID);
        return ResponseEntity.noContent().build();
    }
}
