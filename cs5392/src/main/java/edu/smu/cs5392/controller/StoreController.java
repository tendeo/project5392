package edu.smu.cs5392.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.smu.cs5392.model.Store;
import edu.smu.cs5392.service.StoreService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    // Add a new Store
    @PostMapping
    public ResponseEntity<Store> addStore(@RequestBody Store store) {
        Store savedStore = storeService.saveStore(store);
        return ResponseEntity.ok(savedStore);
    }

    // Get all Stores
    @GetMapping
    public ResponseEntity<List<Store>> getAllStores() {
        List<Store> stores = storeService.getAllStores();
        return ResponseEntity.ok(stores);
    }

    // Get Store by ID
    @GetMapping("/{id}")
    public ResponseEntity<Store> getStoreById(@PathVariable Long id) {
        Optional<Store> store = storeService.getStoreById(id);
        return store.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update Store
    @PutMapping("/{id}")
    public ResponseEntity<Store> updateStore(@PathVariable Long id, @RequestBody Store updatedStore) {
        Store store = storeService.updateStore(id, updatedStore);
        if (store != null) {
            return ResponseEntity.ok(store);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete Store by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStore(@PathVariable Long id) {
        storeService.deleteStore(id);
        return ResponseEntity.noContent().build();
    }
}
