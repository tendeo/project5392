package edu.smu.cs5392.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.smu.cs5392.model.Node;
import edu.smu.cs5392.repository.NodeRepository;

/**
 * Service class for managing Nodes.
 */
@Service
public class NodeService {

    private final NodeRepository nodeRepository;

    public NodeService(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    // Retrieve all nodes
    public List<Node> getAllNodes() {
        return nodeRepository.findAll();
    }

    // Retrieve a node by ID
    public Optional<Node> getNodeById(String id) {
        return nodeRepository.findById(id);
    }

    // Create or update a node
    public Node saveNode(Node node) {
        return nodeRepository.save(node);
    }

    // Delete a node by ID
    public void deleteNodeById(String id) {
        nodeRepository.deleteById(id);
    }

    // Delete all nodes
    public void deleteAllNodes() {
        nodeRepository.deleteAll();
    }
}
