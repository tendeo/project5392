package edu.smu.cs5392.service;

import edu.smu.cs5392.repository.LoginCredentialsRepository;
import edu.smu.cs5392.model.LoginCredentials;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing LoginCredentials.
 */
@Service
public class LoginCredentialsService {

    private final LoginCredentialsRepository repository;

    public LoginCredentialsService(LoginCredentialsRepository repository) {
        this.repository = repository;
    }

    // Get all credentials
    public List<LoginCredentials> getAllCredentials() {
        return repository.findAll();
    }

    // Get credentials by username
    public Optional<LoginCredentials> getByUserName(String userName) {
        return repository.findByUserName(userName);
    }

    // Save or update credentials
    public LoginCredentials save(LoginCredentials credentials) {
        return repository.save(credentials);
    }

    // Delete by username
    public void deleteByUserName(String userName) {
        repository.deleteById(userName);
    }
}

