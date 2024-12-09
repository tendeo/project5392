package edu.smu.cs5392.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.smu.cs5392.model.Node;
import edu.smu.cs5392.repository.NodeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class NodeService {

    @Autowired
    private NodeRepository nodeRepository;

    public Node saveNode(Node node) {
        return nodeRepository.save(node);
    }

    public List<Node> getAllNodes() {
        return nodeRepository.findAll();
    }

    public Optional<Node> getNodeById(String id) {
        return nodeRepository.findById(id);
    }

    public void deleteNode(String id) {
        nodeRepository.deleteById(id);
    }

    public Node changeNodeStatus(String id, boolean status) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'changeNodeStatus'");
    }
}

