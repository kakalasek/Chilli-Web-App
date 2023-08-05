package com.project.chilliwebapp_backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class SeedService {

    @Autowired
    private SeedRepository seedRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

}
