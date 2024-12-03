package edu.smu.cs5392.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.smu.cs5392.model.Store;

import java.util.List;

/**
 * Repository interface for Store entity.
 */
@Repository
public interface StoreRepository extends JpaRepository<Store, String> {

    // Find all entries by storeID
    List<Store> findByStoreID(String storeID);

    // Find all entries by ownerNodeID
    List<Store> findByOwnerNodeID(String ownerNodeID);
}
