package edu.smu.cs5392.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.smu.cs5392.model.Store;
import edu.smu.cs5392.repository.StoreRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    // Save a Store entity
    public Store saveStore(Store store) {
        return storeRepository.save(store);
    }

    // Retrieve all Store entities
    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    // Retrieve a specific Store entity by ID
    public Optional<Store> getStoreById(Long id) {
        return storeRepository.findById(id);
    }

    // Delete a Store entity by ID
    public void deleteStore(Long id) {
        storeRepository.deleteById(id);
    }

    // Update a Store entity
    public Store updateStore(Long id, Store updatedStore) {
        Optional<Store> existingStore = storeRepository.findById(id);
        if (existingStore.isPresent()) {
            Store store = existingStore.get();
            store.setStoreID(updatedStore.getStoreID());
            store.setMessageID(updatedStore.getMessageID());
            store.setTimeReceived(updatedStore.getTimeReceived());
            store.setOwnerNodeID(updatedStore.getOwnerNodeID());
            return storeRepository.save(store);
        }
        return null;
    }
}


