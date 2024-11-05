package edu.smu.cs5392.controller;

import edu.smu.cs5392.service.LoginCredentialsService;
import edu.smu.cs5392.model.LoginCredentials;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST Controller for managing LoginCredentials.
 */
@RestController
@RequestMapping("/api/login-credentials")
public class LoginCredentialsController {

    private final LoginCredentialsService service;

    @Autowired
    public LoginCredentialsController(LoginCredentialsService service) {
        this.service = service;
    }

    // Get all login credentials
    @GetMapping
    public ResponseEntity<List<LoginCredentials>> getAllCredentials() {
        List<LoginCredentials> credentials = service.getAllCredentials();
        return new ResponseEntity<>(credentials, HttpStatus.OK);
    }

    // Get credentials by username
    @GetMapping("/{userName}")
    public ResponseEntity<LoginCredentials> getByUserName(@PathVariable String userName) {
        Optional<LoginCredentials> credentials = service.getByUserName(userName);
        return credentials.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                          .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Create or update login credentials
    @PostMapping
    public ResponseEntity<LoginCredentials> createOrUpdate(@RequestBody LoginCredentials credentials) {
        LoginCredentials savedCredentials = service.save(credentials);
        return new ResponseEntity<>(savedCredentials, HttpStatus.CREATED);
    }

    // Delete credentials by username
    @DeleteMapping("/{userName}")
    public ResponseEntity<HttpStatus> deleteByUserName(@PathVariable String userName) {
        service.deleteByUserName(userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
