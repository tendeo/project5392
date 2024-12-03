package edu.smu.cs5392.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import java.util.Objects;

/**
 * JPA Entity representing a Network.
 */
@Entity
@Table(name = "Network")
public class NetworkEntity {

    @Id
    @Column(name = "NetworkID", nullable = false, unique = true)
    private String networkID;

    @Column(name = "MinSize", nullable = false)
    private Integer minSize;

    @Column(name = "MaxSize", nullable = false)
    private Integer maxSize;

    // Default constructor (required by JPA)
    public NetworkEntity() {
    }

    // Parameterized constructor
    public NetworkEntity(String networkID, Integer minSize, Integer maxSize) {
        this.networkID = networkID;
        this.minSize = minSize;
        this.maxSize = maxSize;
    }

    // Getters and Setters
    public String getNetworkID() {
        return networkID;
    }

    public void setNetworkID(String networkID) {
        this.networkID = networkID;
    }

    public Integer getMinSize() {
        return minSize;
    }

    public void setMinSize(Integer minSize) {
        this.minSize = minSize;
    }

    public Integer getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(Integer maxSize) {
        this.maxSize = maxSize;
    }

    // equals() and hashCode() for entity comparison
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NetworkEntity that = (NetworkEntity) o;
        return Objects.equals(networkID, that.networkID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(networkID);
    }

    // toString() method for debugging
    @Override
    public String toString() {
        return "NetworkEntity{" +
                "networkID='" + networkID + '\'' +
                ", minSize=" + minSize +
                ", maxSize=" + maxSize +
                '}';
    }
}
