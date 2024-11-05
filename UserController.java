package edu.smu.cs5392.controller;

import edu.smu.cs5392.service.UserService;
import edu.smu.cs5392.model.User;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST Controller for managing User entities.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // CREATE or UPDATE a user
    @PostMapping
    public ResponseEntity<User> createOrUpdateUser(@RequestBody User user) {
        try {
            User savedUser = userService.saveUser(user);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // READ: Get all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // READ: Get a user by userName
    @GetMapping("/{userName}")
    public ResponseEntity<User> getUserByUserName(@PathVariable String userName) {
        Optional<User> user = userService.getUserByUserName(userName);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                   .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // DELETE: Delete a user by userName
    @DeleteMapping("/{userName}")
    public ResponseEntity<HttpStatus> deleteUserByUserName(@PathVariable String userName) {
        try {
            userService.deleteUserByUserName(userName);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE: Delete all users
    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllUsers() {
        try {
            userService.deleteAllUsers();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

