package edu.smu.cs5392.backend;

import edu.smu.cs5392.model.*;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Service
public class RingNetwork {

    @PersistenceContext
    private EntityManager entityManager;

    private List<Node> nodes;

    public RingNetwork() {
        this.nodes = new ArrayList<>();
    }

    public void loadNodesFromDatabase() {
        List<Node> nodeList = entityManager.createQuery("SELECT n FROM Node n", Node.class).getResultList();
        nodes.addAll(nodeList);
    }

    @Transactional
    public Node addNode(Node newNode) {
        if (!nodes.isEmpty()) {
            Node lastNode = nodes.get(nodes.size() - 1);
            Node firstNode = nodes.get(0);

            newNode.setLeftNeighborID(lastNode.getNodeID());
            newNode.setRightNeighborID(firstNode.getNodeID());
            lastNode.setRightNeighborID(newNode.getNodeID());
            firstNode.setLeftNeighborID(newNode.getNodeID());

            entityManager.persist(newNode);
            entityManager.merge(lastNode);
            entityManager.merge(firstNode);
        }

        nodes.add(newNode);
        return newNode;
    }

    @Transactional
    public Node removeNode(String nodeID) {
        Node nodeToRemove = findNodeById(nodeID);

        if (nodeToRemove != null && nodes.size() > 1) {
            Node leftNeighbor = findNodeById(nodeToRemove.getLeftNeighborID());
            Node rightNeighbor = findNodeById(nodeToRemove.getRightNeighborID());

            if (leftNeighbor != null) {
                leftNeighbor.setRightNeighborID(nodeToRemove.getRightNeighborID());
            }
            if (rightNeighbor != null) {
                rightNeighbor.setLeftNeighborID(nodeToRemove.getLeftNeighborID());
            }

            entityManager.remove(entityManager.contains(nodeToRemove) ? nodeToRemove : entityManager.merge(nodeToRemove));
            entityManager.merge(leftNeighbor);
            entityManager.merge(rightNeighbor);

            nodes.remove(nodeToRemove);
        }

        return nodeToRemove;
    }

    @Transactional
    public Node changeNodeStatus(String nodeID, boolean status) {
        Node node = findNodeById(nodeID);

        if (node != null) {
            node.setStatus(status);
            entityManager.merge(node);
        }

        return node;
    }

    public List<Node> getAllNodes() {
        printRingNetwork();
        return this.nodes;
    }

    private Node findNodeById(String nodeID) {
        return nodes.stream()
                .filter(node -> node.getNodeID().equals(nodeID))
                .findFirst()
                .orElse(null);
    }

    /**
     * Sends a message from sender node to receiver node and updates the path.
     * @param senderNodeID ID of the sender node.
     * @param receiverNodeID ID of the receiver node.
     * @param messageContent The message content to send.
     */
    public void sendMessage(String senderNodeID, String receiverNodeID, String messageContent) {
        Node senderNode = findNodeById(senderNodeID);
        Node receiverNode = findNodeById(receiverNodeID);

        if (senderNode == null || receiverNode == null) {
            throw new IllegalArgumentException("Invalid sender or receiver node ID");
        }

        StringBuilder path = new StringBuilder(senderNodeID);
        Node currentNode = senderNode;

        while (!currentNode.getNodeID().equals(receiverNodeID)) {
            String nextNodeID = currentNode.getRightNeighborID();
            currentNode = findNodeById(nextNodeID);

            if (currentNode == null) {
                throw new IllegalStateException("Node not found in the ring network");
            }

            path.append(" -> ").append(currentNode.getNodeID());
        }

        System.out.println("Message Sent: " + messageContent);
        System.out.println("Message Path: " + path.toString());
    }


   /**
     * Reads a message identified by the given message ID.
     * @param nodeID The ID of the message to read.
     * @return The message content if found, otherwise null.
     */
    @Transactional
    public List<Message> readMessages(String nodeID) {
        return new ArrayList<Message>();
    }


    /**
     * Reads a message identified by the given message ID.
     * @param messageID The ID of the message to read.
     * @return The message content if found, otherwise null.
     */
    @Transactional
    public String readMessage(String messageID) {

        return "Not implemented";

    /* 
        Message message = entityManager.find(Message.class, messageID);
        if (message == null) {
            throw new IllegalArgumentException("Message with ID " + messageID + " not found");
        }
        return message.getContent();
        */
    }


    /**
     * Prints the ring network topology.
     */
    public void printRingNetwork() {
        if (nodes.isEmpty()) {
            System.out.println("The ring network is empty.");
            return;
        }

        System.out.println("Ring Network Topology:");
        nodes.forEach(node -> {
            System.out.println(
                "NodeID: " + node.getNodeID() +
                ", LeftNeighborID: " + node.getLeftNeighborID() +
                ", RightNeighborID: " + node.getRightNeighborID() +
                ", Status: " + (node.isStatus() ? "Active" : "Inactive")
            );
        });
    }
}



