package com.project.chilliwebapp_backend.seed;

import com.project.chilliwebapp_backend.seed.Seed;
import com.project.chilliwebapp_backend.seed.SeedRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SeedService {

    @Autowired
    private SeedRepository seedRepository;


    public List<Seed> allSeeds(){
        return seedRepository.findAll();
    }

    public List<Seed> allSeedsByAge(Boolean asc){
        if(asc) return seedRepository.findAll(Sort.by(Sort.Direction.ASC, "dateOfStoring"));
        else return seedRepository.findAll(Sort.by(Sort.Direction.DESC, "dateOfStoring"));
    }

    public Seed createSeed(String type, LocalDate dateOfStoring, Integer count){
        Seed seed = seedRepository.insert(new Seed(type,dateOfStoring,count));

        return seed;
    }

    public void removeSeed(ObjectId id) {
        seedRepository.deleteById(id);
    }
}
