package edu.smu.cs5392.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.smu.cs5392.model.Store;
import edu.smu.cs5392.service.StoreService;

import java.util.List;
import java.util.Optional;

/**
 * REST Controller for managing Store entries.
 */
@RestController
@RequestMapping("/api/stores")
public class StoreController {

    private final StoreService service;

    public StoreController(StoreService service) {
        this.service = service;
    }

    // Get all store entries
    @GetMapping
    public ResponseEntity<List<Store>> getAllStores() {
        List<Store> stores = service.getAllStores();
        return new ResponseEntity<>(stores, HttpStatus.OK);
    }

    // Get store entry by messageID
    @GetMapping("/{messageID}")
    public ResponseEntity<Store> getByMessageID(@PathVariable String messageID) {
        Optional<Store> store = service.getByMessageID(messageID);
        return store.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Get all store entries by storeID
    @GetMapping("/store/{storeID}")
    public ResponseEntity<List<Store>> getByStoreID(@PathVariable String storeID) {
        List<Store> stores = service.getByStoreID(storeID);
        return new ResponseEntity<>(stores, HttpStatus.OK);
    }

    // Get all store entries by ownerNodeID
    @GetMapping("/owner/{ownerNodeID}")
    public ResponseEntity<List<Store>> getByOwnerNodeID(@PathVariable String ownerNodeID) {
        List<Store> stores = service.getByOwnerNodeID(ownerNodeID);
        return new ResponseEntity<>(stores, HttpStatus.OK);
    }

    // Create or update a store entry
    @PostMapping
    public ResponseEntity<Store> createOrUpdate(@RequestBody Store store) {
        Store savedStore = service.save(store);
        return new ResponseEntity<>(savedStore, HttpStatus.CREATED);
    }

    // Delete a store entry by messageID
    @DeleteMapping("/{messageID}")
    public ResponseEntity<HttpStatus> deleteByMessageID(@PathVariable String messageID) {
        service.deleteByMessageID(messageID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
