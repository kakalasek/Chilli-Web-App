package com.project.chilliwebapp_backend.plant;

import com.project.chilliwebapp_backend.plant.Plant;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface PlantRepository extends MongoRepository<Plant, ObjectId> {
    Page<Plant> findAllByDateOfDisposalIsNotNull(Pageable pageable);
    Page<Plant> findAllByDateOfDisposalIsNull(Pageable pageable);
}
