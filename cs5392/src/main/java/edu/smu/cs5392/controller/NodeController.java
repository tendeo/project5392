package edu.smu.cs5392.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.smu.cs5392.model.Node;
import edu.smu.cs5392.service.NodeService;

import java.util.List;

import java.util.Optional;

@RestController
@RequestMapping("/api/node")
public class NodeController {

    @Autowired
    private NodeService nodeService;

    // Add a new Node
    @PostMapping
    public ResponseEntity<Node> addNode(@RequestBody Node node) {
        Node savedNode = nodeService.saveNode(node);
        return ResponseEntity.ok(savedNode);
    }

    // Get all Nodes
    @GetMapping
    public ResponseEntity<List<Node>> getAllNodes() {
        List<Node> nodes = nodeService.getAllNodes();
        return ResponseEntity.ok(nodes);
    }

    // Get Node by ID
    @GetMapping("/{id}")
    public ResponseEntity<Node> getNodeById(@PathVariable String id) {
        Optional<Node> node = nodeService.getNodeById(id);
        return node.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
/* 
    // Update Node
    @PutMapping("/{id}")
    public ResponseEntity<Node> updateNode(@PathVariable String id, @RequestBody Node updatedNode) {
        Node node = nodeService.updateNode(id, updatedNode);
        if (node != null) {
            return ResponseEntity.ok(node);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
        */

    // Delete Node by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNode(@PathVariable String id) {
        nodeService.deleteNode(id);
        return ResponseEntity.noContent().build();
    }
/* 
    // Get Neighbors of a Node
    @GetMapping("/{id}/neighbors")
    public ResponseEntity<List<Node>> getNeighbors(@PathVariable String id) {
        List<Node> neighbors = nodeService.getNeighbors(id);
        if (neighbors != null) {
            return ResponseEntity.ok(neighbors);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    */
    // Change the status of a Node
    @PatchMapping("/{id}/status")
    public ResponseEntity<Node> changeNodeStatus(@PathVariable String id, @RequestParam boolean status) {
        Node node = nodeService.changeNodeStatus(id, status);
        if (node != null) {
            return ResponseEntity.ok(node);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
