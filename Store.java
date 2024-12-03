package edu.smu.cs5392.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * JPA Entity representing the Store table.
 */
@Entity
@Table(name = "Store")
public class Store {

    @Column(name = "StoreID", nullable = false)
    private String storeID;

    @Id
    @Column(name = "MessageID", nullable = false, unique = true)
    private String messageID;

    @Column(name = "TimeReceived", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeReceived;

    @Column(name = "OwnerNodeID", nullable = false)
    private String ownerNodeID;

    // Default constructor
    public Store() {}

    // Parameterized constructor
    public Store(String storeID, String messageID, Date timeReceived, String ownerNodeID) {
        this.storeID = storeID;
        this.messageID = messageID;
        this.timeReceived = timeReceived;
        this.ownerNodeID = ownerNodeID;
    }

    // Getters and Setters
    public String getStoreID() { return storeID; }
    public void setStoreID(String storeID) { this.storeID = storeID; }

    public String getMessageID() { return messageID; }
    public void setMessageID(String messageID) { this.messageID = messageID; }

    public Date getTimeReceived() { return timeReceived; }
    public void setTimeReceived(Date timeReceived) { this.timeReceived = timeReceived; }

    public String getOwnerNodeID() { return ownerNodeID; }
    public void setOwnerNodeID(String ownerNodeID) { this.ownerNodeID = ownerNodeID; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Store store = (Store) o;
        return Objects.equals(messageID, store.messageID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageID);
    }

    @Override
    public String toString() {
        return "Store{" +
                "storeID='" + storeID + '\'' +
                ", messageID='" + messageID + '\'' +
                ", timeReceived=" + timeReceived +
                ", ownerNodeID='" + ownerNodeID + '\'' +
                '}';
    }
}

