package edu.smu.cs5392.service;

import edu.smu.cs5392.repository.NetworkRepository;
import edu.smu.cs5392.model.NetworkEntity;


import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing NetworkEntity instances.
 */
@Service
public class NetworkService {

    private final NetworkRepository networkRepository;

    public NetworkService(NetworkRepository networkRepository) {
        this.networkRepository = networkRepository;
    }

    // Retrieve all networks
    public List<NetworkEntity> getAllNetworks() {
        return networkRepository.findAll();
    }

    // Retrieve a network by ID
    public Optional<NetworkEntity> getNetworkById(String id) {
        return networkRepository.findById(id);
    }

    // Create or update a network
    public NetworkEntity saveNetwork(NetworkEntity networkEntity) {
        return networkRepository.save(networkEntity);
    }

    // Delete a network by ID
    public void deleteNetworkById(String id) {
        networkRepository.deleteById(id);
    }

    // Delete all networks
    public void deleteAllNetworks() {
        networkRepository.deleteAll();
    }
}
