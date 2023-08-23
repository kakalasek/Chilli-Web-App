package com.project.chilliwebapp_backend.plant;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
/**
 * Directly communicates with the db. An object ('plant') and an id type ('ObjectdId') have to be defined
 */
public interface PlantRepository extends MongoRepository<Plant, ObjectId> {
    Page<Plant> findAllByDateOfDisposalIsNotNull(Pageable pageable); // Returns a page of all plants with date of disposal
    Page<Plant> findAllByDateOfDisposalIsNull(Pageable pageable); // Returns a page of all plants without date of disposal
    List<Plant> findAllByDateOfDisposalIsNull(); // Returns all plants without date of disposal
}
