package edu.smu.cs5392.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.smu.cs5392.model.User;
import edu.smu.cs5392.model.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    void deleteByUser(User user);
}
