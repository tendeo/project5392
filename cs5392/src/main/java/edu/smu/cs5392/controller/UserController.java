package edu.smu.cs5392.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.smu.cs5392.service.CustomUserDetailsService;

/* 
* API Endpoints
- Add Operator User
  POST /api/users/operator
  Request Parameters:
   userName
   password
   firstName
   lastName
   email

- Delete User
  DELETE /api/users/{userId}

*/


@RestController
@RequestMapping("/api/users")
public class UserController {


    private final CustomUserDetailsService userService;

    public UserController(CustomUserDetailsService userService) {
        this.userService = userService;
    }

    @DeleteMapping("/username/{userName}")
    public String deleteUserByUserName(@PathVariable String userName) {
        userService.deleteByUserName(userName);
        return "User with username " + userName + " has been deleted.";
    }

        // Add a new user with Operator role
    @PostMapping("/operator")
    public ResponseEntity<String> addOperatorUser(
            @RequestParam String userName,
            @RequestParam String password,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String email) {

            System.out.println("addOperator userName=" + userName);
        try {
            userService.addOperatorUser(userName, password, firstName, lastName, email);
            return ResponseEntity.ok("Operator user added successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Delete a user
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        try {
            userService.deleteUser(userId);
            return ResponseEntity.ok("User deleted successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
