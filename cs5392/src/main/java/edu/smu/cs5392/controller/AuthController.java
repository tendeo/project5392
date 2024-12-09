package edu.smu.cs5392.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @GetMapping("/admin")
    public String adminAccess() {
        return "Welcome, Admin!";
    }

    @GetMapping("/operator")
    public String operatorAccess() {
        return "Welcome, Operator!";
    }
}
