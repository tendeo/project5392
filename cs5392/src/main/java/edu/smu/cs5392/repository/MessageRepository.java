package edu.smu.cs5392.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.smu.cs5392.model.Message;

public interface MessageRepository extends JpaRepository<Message, String> {
}
