package com.project.chilliwebapp_backend.seed;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
/**
 * Directly communicates with the db. An object ('seed') and an id type ('ObjectdId') have to be defined
 */
public interface SeedRepository extends MongoRepository<Seed, ObjectId> {
}
