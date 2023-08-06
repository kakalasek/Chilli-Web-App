package com.project.chilliwebapp_backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SeedService {

    @Autowired
    private SeedRepository seedRepository;


    public List<Seed> allSeeds(){
        return seedRepository.findAll();
    }

    public Seed createSeed(String type, LocalDate dayOfStoring, Integer count){
        Seed seed = seedRepository.insert(new Seed(type,dayOfStoring,count));

        return seed;
    }
}
