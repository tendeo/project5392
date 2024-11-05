package edu.smu.cs5392.controller;

import edu.smu.cs5392.model.Node;
import edu.smu.cs5392.service.NodeService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST Controller for managing Node entities.
 */
@RestController
@RequestMapping("/api/nodes")
public class NodeController {

    private final NodeService nodeService;

    public NodeController(NodeService nodeService) {
        this.nodeService = nodeService;
    }

    // CREATE or UPDATE a node
    @PostMapping
    public ResponseEntity<Node> createOrUpdateNode(@RequestBody Node node) {
        try {
            Node savedNode = nodeService.saveNode(node);
            return new ResponseEntity<>(savedNode, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // READ: Get all nodes
    @GetMapping
    public ResponseEntity<List<Node>> getAllNodes() {
        List<Node> nodes = nodeService.getAllNodes();
        if (nodes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(nodes, HttpStatus.OK);
    }

    // READ: Get a node by ID
    @GetMapping("/{id}")
    public ResponseEntity<Node> getNodeById(@PathVariable String id) {
        Optional<Node> node = nodeService.getNodeById(id);
        return node.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                   .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // DELETE: Delete a node by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteNodeById(@PathVariable String id) {
        try {
            nodeService.deleteNodeById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE: Delete all nodes
    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllNodes() {
        try {
            nodeService.deleteAllNodes();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

