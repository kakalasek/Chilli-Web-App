package com.project.chilliwebapp_backend.seed;

import com.project.chilliwebapp_backend.seed.Seed;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeedRepository extends MongoRepository<Seed, ObjectId> {
}
