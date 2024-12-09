package edu.smu.cs5392.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Inbox", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"InboxID", "MessageID"})
})
public class Inbox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "InboxID", nullable = false)
    private String inboxID;

    @Column(name = "MessageID", nullable = false)
    private String messageID;

    @Column(name = "TimeReceived")
    private LocalDateTime timeReceived;

    @Column(name = "OwnerNodeID")
    private String ownerNodeID;

    @Column(name = "InboxSize")
    private Integer inboxSize;

    @ManyToOne
    @JoinColumn(name = "NodeID", referencedColumnName = "NodeID")
    private Node node;

    // Constructors
    public Inbox() {}

    public Inbox(String inboxID, String messageID, LocalDateTime timeReceived, String ownerNodeID, Integer inboxSize) {
        this.inboxID = inboxID;
        this.messageID = messageID;
        this.timeReceived = timeReceived;
        this.ownerNodeID = ownerNodeID;
        this.inboxSize = inboxSize;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getInboxID() {
        return inboxID;
    }

    public void setInboxID(String inboxID) {
        this.inboxID = inboxID;
    }

    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public LocalDateTime getTimeReceived() {
        return timeReceived;
    }

    public void setTimeReceived(LocalDateTime timeReceived) {
        this.timeReceived = timeReceived;
    }

    public String getOwnerNodeID() {
        return ownerNodeID;
    }

    public void setOwnerNodeID(String ownerNodeID) {
        this.ownerNodeID = ownerNodeID;
    }

    public Integer getInboxSize() {
        return inboxSize;
    }

    public void setInboxSize(Integer inboxSize) {
        this.inboxSize = inboxSize;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }
}



