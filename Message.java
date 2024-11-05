package edu.smu.cs5392.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * JPA Entity representing a Message.
 */
@Entity
@Table(name = "Message")
public class Message {

    @Id
    @Column(name = "MessageID", nullable = false, unique = true)
    private String messageID;

    @Column(name = "SenderID", nullable = false)
    private String senderID;

    @Column(name = "ReceiverID", nullable = false)
    private String receiverID;

    @Column(name = "TimeStampCreated", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeStampCreated;

    @Column(name = "TimeStampReceived")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeStampReceived;

    @Column(name = "Direction", nullable = false)
    private String direction;

    @Column(name = "Path", nullable = false)
    private String path;

    @Column(name = "Status", nullable = false)
    private String status;

    @Column(name = "Message", nullable = false, length = 500)
    private String message;

    // Default constructor required by JPA
    public Message() {}

    // Parameterized constructor
    public Message(String messageID, String senderID, String receiverID, Date timeStampCreated, 
                   Date timeStampReceived, String direction, String path, String status, String message) {
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
    public String getMessageID() { return messageID; }
    public void setMessageID(String messageID) { this.messageID = messageID; }

    public String getSenderID() { return senderID; }
    public void setSenderID(String senderID) { this.senderID = senderID; }

    public String getReceiverID() { return receiverID; }
    public void setReceiverID(String receiverID) { this.receiverID = receiverID; }

    public Date getTimeStampCreated() { return timeStampCreated; }
    public void setTimeStampCreated(Date timeStampCreated) { this.timeStampCreated = timeStampCreated; }

    public Date getTimeStampReceived() { return timeStampReceived; }
    public void setTimeStampReceived(Date timeStampReceived) { this.timeStampReceived = timeStampReceived; }

    public String getDirection() { return direction; }
    public void setDirection(String direction) { this.direction = direction; }

    public String getPath() { return path; }
    public void setPath(String path) { this.path = path; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    // equals() and hashCode() based on primary key (messageID)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(messageID, message.messageID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageID);
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageID='" + messageID + '\'' +
                ", senderID='" + senderID + '\'' +
                ", receiverID='" + receiverID + '\'' +
                ", timeStampCreated=" + timeStampCreated +
                ", timeStampReceived=" + timeStampReceived +
                ", direction='" + direction + '\'' +
                ", path='" + path + '\'' +
                ", status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
