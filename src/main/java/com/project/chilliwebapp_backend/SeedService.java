package com.project.chilliwebapp_backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeedService {

    @Autowired
    private SeedRepository seedRepository;

    public List<Seed> allSeeds(){
        return seedRepository.findAll();
    }
}
