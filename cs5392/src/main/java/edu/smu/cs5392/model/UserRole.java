package edu.smu.cs5392.model;

import jakarta.persistence.*;

@Entity
@Table(name = "UserRoles")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    // Default constructor
    public UserRole() {
    }

    // Constructor with fields
    public UserRole(User user, Role role) {
        this.user = user;
        this.role = role;
    }

    // Getters and Setters
    public Long getId() {
       return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    // ToString method
    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", user=" + (user != null ? user.getId() : null) +
                ", role=" + (role != null ? role.getName() : null) +
                '}';
    }
}

