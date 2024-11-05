package edu.smu.cs5392.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "SystemBuffer")
public class SystemBuffer {

    @Id
    @Column(name = "MessageID")
    private String messageID;

    @Column(name = "TimeStored")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeStored;


    // Default constructor
    public SystemBuffer() {}

    public SystemBuffer(String messageID, Date timeStored) {
        this.messageID = messageID;
        this.timeStored = timeStored;
    }

    // Getters and Setters
    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public Date getTimeStored() {
        return timeStored;
    }

    public void setTimeStored(Date timeStored) {
        this.timeStored = timeStored;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SystemBuffer buffer = (SystemBuffer) o;
        return Objects.equals(messageID, buffer.messageID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageID);
    }

    @Override
    public String toString() {
        return "SystemBuffer{" +
                "messageID='" + messageID + '\'' +
                ", timeStored=" + timeStored +
                '}';
    }
}
