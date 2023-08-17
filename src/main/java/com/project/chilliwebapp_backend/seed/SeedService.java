package com.project.chilliwebapp_backend.seed;

import com.project.chilliwebapp_backend.seed.Seed;
import com.project.chilliwebapp_backend.seed.SeedRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SeedService {

    @Autowired
    private SeedRepository seedRepository;


    public Page<Seed> allSeeds(Integer page){
        PageRequest pr = PageRequest.of(page, 10);
        return seedRepository.findAll(pr);
    }

    public Page<Seed> allSeedsByAge(Integer page, Boolean asc){
        PageRequest pr = PageRequest.of(page, 10);
        if(asc) return seedRepository.findAll(pr.withSort(Sort.Direction.ASC, "dateOfStoring"));
        else return seedRepository.findAll(pr.withSort(Sort.Direction.DESC, "dateOfStoring"));
    }

    public Seed createSeed(String type, LocalDate dateOfStoring, Integer count){
        Seed seed = seedRepository.insert(new Seed(type,dateOfStoring,count));

        return seed;
    }

    public void removeSeed(ObjectId id) {
        seedRepository.deleteById(id);
    }
}
