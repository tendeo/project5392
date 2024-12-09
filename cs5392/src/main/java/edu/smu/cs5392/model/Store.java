package edu.smu.cs5392.model;

import jakarta.persistence.*;


import java.time.LocalDateTime;

@Entity
@Table(name = "Store", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"StoreID", "MessageID"})
})
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "StoreID", nullable = false)
    private String storeID;

    @Column(name = "MessageID", nullable = false)
    private String messageID;

    @Column(name = "TimeReceived")
    private LocalDateTime timeReceived;

    @Column(name = "OwnerNodeID")
    private String ownerNodeID;

    @ManyToOne
    @JoinColumn(name = "NodeID", referencedColumnName = "NodeID")
    private Node node;

    // Constructors
    public Store() {}

    public Store(String storeID, String messageID, LocalDateTime timeReceived, String ownerNodeID) {
        this.storeID = storeID;
        this.messageID = messageID;
        this.timeReceived = timeReceived;
        this.ownerNodeID = ownerNodeID;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getStoreID() {
        return storeID;
    }

    public void setStoreID(String storeID) {
        this.storeID = storeID;
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

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }
}



