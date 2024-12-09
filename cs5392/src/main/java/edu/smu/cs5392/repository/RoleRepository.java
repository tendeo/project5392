package edu.smu.cs5392.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.smu.cs5392.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
