package edu.smu.cs5392.controller;

import edu.smu.cs5392.service.NetworkService;
import edu.smu.cs5392.model.NetworkEntity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST Controller for managing NetworkEntity instances.
 */
@RestController
@RequestMapping("/api/networks")
public class NetworkController {

    private final NetworkService networkService;

    public NetworkController(NetworkService networkService) {
        this.networkService = networkService;
    }

    // CREATE or UPDATE a network
    @PostMapping
    public ResponseEntity<NetworkEntity> createOrUpdateNetwork(@RequestBody NetworkEntity networkEntity) {
        try {
            NetworkEntity savedNetwork = networkService.saveNetwork(networkEntity);
            return new ResponseEntity<>(savedNetwork, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // READ: Get all networks
    @GetMapping
    public ResponseEntity<List<NetworkEntity>> getAllNetworks() {
        List<NetworkEntity> networks = networkService.getAllNetworks();
        if (networks.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(networks, HttpStatus.OK);
    }

    // READ: Get a network by ID
    @GetMapping("/{id}")
    public ResponseEntity<NetworkEntity> getNetworkById(@PathVariable String id) {
        Optional<NetworkEntity> networkEntity = networkService.getNetworkById(id);
        return networkEntity.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // DELETE: Delete a network by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteNetworkById(@PathVariable String id) {
        try {
            networkService.deleteNetworkById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE: Delete all networks
    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllNetworks() {
        try {
            networkService.deleteAllNetworks();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
