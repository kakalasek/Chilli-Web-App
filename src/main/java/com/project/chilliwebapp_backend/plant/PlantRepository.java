package com.project.chilliwebapp_backend.plant;

import com.project.chilliwebapp_backend.plant.Plant;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantRepository extends MongoRepository<Plant, ObjectId> {
}
