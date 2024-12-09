package edu.smu.cs5392.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Message")
public class Message {

    @Id
    @Column(name = "MessageID", nullable = false)
    private String messageID;

    @Column(name = "SenderID", nullable = false)
    private String senderID;

    @Column(name = "ReceiverID", nullable = false)
    private String receiverID;

    @Column(name = "TimeStampCreated", nullable = false)
    private LocalDateTime timeStampCreated;

    @Column(name = "TimeStampReceived", nullable = false)
    private LocalDateTime timeStampReceived;

    @Column(name = "Direction", nullable = false)
    private String direction;

    @Column(name = "Path", nullable = false)
    private String path;

    @Column(name = "Status", nullable = false)
    private String status;

    @Column(name = "Message", nullable = false)
    private String message;

    // Constructors
    public Message() {}

    public Message(String messageID, String senderID, String receiverID, LocalDateTime timeStampCreated, 
                   LocalDateTime timeStampReceived, String direction, String path, String status, String message) {
        this.messageID = messageID;
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.timeStampCreated = timeStampCreated;
        this.timeStampReceived = timeStampReceived;
        this.direction = direction;
        this.path = path;
        this.status = status;
        this.message = message;
    }

    // Getters and Setters
    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public String getSenderID() {
        return senderID;
    }

    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }

    public String getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(String receiverID) {
        this.receiverID = receiverID;
    }

    public LocalDateTime getTimeStampCreated() {
        return timeStampCreated;
    }

    public void setTimeStampCreated(LocalDateTime timeStampCreated) {
        this.timeStampCreated = timeStampCreated;
    }

    public LocalDateTime getTimeStampReceived() {
        return timeStampReceived;
    }

    public void setTimeStampReceived(LocalDateTime timeStampReceived) {
        this.timeStampReceived = timeStampReceived;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}



