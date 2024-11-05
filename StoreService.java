package edu.smu.cs5392.service;

import edu.smu.cs5392.model.Store;
import edu.smu.cs5392.repository.StoreRepository;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing Store entities.
 */
@Service
public class StoreService {

    private final StoreRepository repository;

    public StoreService(StoreRepository repository) {
        this.repository = repository;
    }

    // Get all store entries
    public List<Store> getAllStores() {
        return repository.findAll();
    }

    // Get a store entry by messageID
    public Optional<Store> getByMessageID(String messageID) {
        return repository.findById(messageID);
    }

    // Get all store entries by storeID
    public List<Store> getByStoreID(String storeID) {
        return repository.findByStoreID(storeID);
    }

    // Get all store entries by ownerNodeID
    public List<Store> getByOwnerNodeID(String ownerNodeID) {
        return repository.findByOwnerNodeID(ownerNodeID);
    }

    // Save or update a store entry
    public Store save(Store store) {
        return repository.save(store);
    }

    // Delete a store entry by messageID
    public void deleteByMessageID(String messageID) {
        repository.deleteById(messageID);
    }
}
