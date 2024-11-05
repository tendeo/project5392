package edu.smu.cs5392.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import java.util.Objects;

/**
 * JPA Entity representing a Node.
 */
@Entity
@Table(name = "Node")
public class Node {

    @Id
    @Column(name = "NodeID", nullable = false, unique = true)
    private String nodeID;

    @Column(name = "NetworkID", nullable = false)
    private String networkID;

    @Column(name = "LeftNeighborID")
    private String leftNeighborID;

    @Column(name = "RightNeighborID")
    private String rightNeighborID;

    @Column(name = "InboxID")
    private String inboxID;

    @Column(name = "StoreID")
    private String storeID;

    @Column(name = "Status", nullable = false)
    private Boolean status;

    // Default constructor (required by JPA)
    public Node() {
    }

    // Parameterized constructor
    public Node(String nodeID, String networkID, String leftNeighborID, 
                String rightNeighborID, 
                String inboxID, String storeID, Boolean status) {
        this.nodeID = nodeID;
        this.networkID = networkID;
        this.leftNeighborID = leftNeighborID;
        this.rightNeighborID = rightNeighborID;
        this.inboxID = inboxID;
        this.storeID = storeID;
        this.status = status;
    }

    // Getters and Setters
    public String getNodeID() { return nodeID; }
    public void setNodeID(String nodeID) { this.nodeID = nodeID; }

    public String getNetworkID() { return networkID; }
    public void setNetworkID(String networkID) { this.networkID = networkID; }

    public String getLeftNeighborID() { return leftNeighborID; }
    public void setLeftNeighborID(String leftNeighborID) { this.leftNeighborID = leftNeighborID; }

    public String getRightNeighborID() { return rightNeighborID; }
    public void setRightNeighborID(String rightNeighborID) { this.rightNeighborID = rightNeighborID; }

    public String getInboxID() { return inboxID; }
    public void setInboxID(String inboxID) { this.inboxID = inboxID; }

    public String getStoreID() { return storeID; }
    public void setStoreID(String storeID) { this.storeID = storeID; }

    public Boolean getStatus() { return status; }
    public void setStatus(Boolean status) { this.status = status; }

    // equals() and hashCode() for entity comparison
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(nodeID, node.nodeID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeID);
    }

    // toString() method for debugging
    @Override
    public String toString() {
        return "Node{" +
                "nodeID='" + nodeID + '\'' +
                ", networkID='" + networkID + '\'' +
                ", leftNeighborID='" + leftNeighborID + '\'' +
                ", rightNeighborID='" + rightNeighborID + '\'' +
                ", inboxID=" + inboxID +
                ", storeID=" + storeID +
                ", status=" + status +
                '}';
    }
}

