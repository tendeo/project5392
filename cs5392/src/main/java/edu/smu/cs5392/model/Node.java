package edu.smu.cs5392.model;

import jakarta.persistence.*;

import java.util.List;

import java.util.ArrayList;

@Entity
@Table(name = "Node")
public class 
Node {

    @Id
    @Column(name = "NodeID", nullable = false)
    private String nodeID;

    @Column(name = "NetworkID", nullable = false)
    private String networkID;

    @Column(name = "LeftNeighborID", nullable = false)
    private String leftNeighborID;

    @Column(name = "RightNeighborID", nullable = false)
    private String rightNeighborID;

    @Column(name = "Status", nullable = false)
    private boolean status;

    @OneToMany(mappedBy = "node", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Inbox> inboxList = new ArrayList<>();

    @OneToMany(mappedBy = "node", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Store> storeList = new ArrayList<>();

    // Constructors
    public Node() {}

    public Node(String nodeID, String networkID, String leftNeighborID, String rightNeighborID, boolean status) {
        this.nodeID = nodeID;
        this.networkID = networkID;
        this.leftNeighborID = leftNeighborID;
        this.rightNeighborID = rightNeighborID;
        this.status = status;
    }

    // Getters and Setters
    public String getNodeID() {
        return nodeID;
    }

    public void setNodeID(String nodeID) {
        this.nodeID = nodeID;
    }

    public String getNetworkID() {
        return networkID;
    }

    public void setNetworkID(String networkID) {
        this.networkID = networkID;
    }

    public String getLeftNeighborID() {
        return leftNeighborID;
    }

    public void setLeftNeighborID(String leftNeighborID) {
        this.leftNeighborID = leftNeighborID;
    }

    public String getRightNeighborID() {
        return rightNeighborID;
    }

    public void setRightNeighborID(String rightNeighborID) {
        this.rightNeighborID = rightNeighborID;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Inbox> getInboxList() {
        return inboxList;
    }

    public void setInboxList(List<Inbox> inboxList) {
        this.inboxList = inboxList;
    }

    public List<Store> getStoreList() {
        return storeList;
    }

    public void setStoreList(List<Store> storeList) {
        this.storeList = storeList;
    }

    
}




