package edu.smu.cs5392.service;

import edu.smu.cs5392.repository.UserRepository;
import edu.smu.cs5392.model.User;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing User entities.
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Retrieve all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Retrieve a user by userName
    public Optional<User> getUserByUserName(String userName) {
        return userRepository.findById(userName);
    }

    // Create or update a user
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Delete a user by userName
    public void deleteUserByUserName(String userName) {
        userRepository.deleteById(userName);
    }

    // Delete all users
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }
}
