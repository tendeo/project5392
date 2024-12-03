package edu.smu.cs5392.model;

import jakarta.persistence.*;
import java.util.Objects;

/**
 * JPA Entity representing the LoginCredentials table.
 */
@Entity
@Table(name = "LoginCredentials")
public class LoginCredentials {

    @Id
    @Column(name = "Username", nullable = false, unique = true)
    private String userName;

    @Column(name = "Password", nullable = false)
    private String password;

    // Default constructor
    public LoginCredentials() {}

    // Parameterized constructor
    public LoginCredentials(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    // Getters and Setters
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginCredentials that = (LoginCredentials) o;
        return Objects.equals(userName, that.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName);
    }

    @Override
    public String toString() {
        return "LoginCredentials{" +
                "userName='" + userName + '\'' +
                ", password='[PROTECTED]'}";
    }
}

