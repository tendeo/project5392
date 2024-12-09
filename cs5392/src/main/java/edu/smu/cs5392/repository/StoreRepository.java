package edu.smu.cs5392.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.smu.cs5392.model.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {
   // Optional<Store> findByOwnerNodeID(String ownerNodeID);
}
