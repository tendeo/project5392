package edu.smu.cs5392.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * JPA Entity representing an Inbox.
 */
@Entity
@Table(name = "Inbox")
public class Inbox {

    @Id
    @Column(name = "MessageID", nullable = false, unique = true)
    private String messageID;

    @Column(name = "InboxID", nullable = false)
    private String inboxID;

    @Column(name = "TimeReceived", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeReceived;

    @Column(name = "OwnerNodeID", nullable = false)
    private String ownerNodeID;

    @Column(name = "Size", nullable = false)
    private Integer size;

    // Default constructor (required by JPA)
    public Inbox() {}

    // Parameterized constructor
    public Inbox(String messageID, String inboxID, Date timeReceived, String ownerNodeID, Integer size) {
        this.messageID = messageID;
        this.inboxID = inboxID;
        this.timeReceived = timeReceived;
        this.ownerNodeID = ownerNodeID;
        this.size = size;
    }

    // Getters and Setters
    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public String getInboxID() {
        return inboxID;
    }

    public void setInboxID(String inboxID) {
        this.inboxID = inboxID;
    }

    public Date getTimeReceived() {
        return timeReceived;
    }

    public void setTimeReceived(Date timeReceived) {
        this.timeReceived = timeReceived;
    }

    public String getOwnerNodeID() {
        return ownerNodeID;
    }

    public void setOwnerNodeID(String ownerNodeID) {
        this.ownerNodeID = ownerNodeID;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    // equals() and hashCode() based on primary key (messageID)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inbox inbox = (Inbox) o;
        return Objects.equals(messageID, inbox.messageID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageID);
    }

    // toString() method for debugging
    @Override
    public String toString() {
        return "Inbox{" +
                "messageID='" + messageID + '\'' +
                ", inboxID='" + inboxID + '\'' +
                ", timeReceived=" + timeReceived +
                ", ownerNodeID='" + ownerNodeID + '\'' +
                ", size=" + size +
                '}';
    }
}
