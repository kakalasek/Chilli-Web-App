package com.project.chilliwebapp_backend.seed;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
/**
 * Communicates with the repository and handles all internal logic of the request
 */
public class SeedService {

    @Autowired // Automatically injects the property
    private SeedRepository seedRepository;

    /**
     *
     * @param page Page number
     * @return Page of 10 seeds, unordered
     */
    public Page<Seed> allSeeds(Integer page){
        PageRequest pr = PageRequest.of(page, 10); // Each page is 10 items long
        return seedRepository.findAll(pr);
    }

    /**
     *
     * @param page Page number
     * @param asc True=ASC/False=DESC
     * @return Page of 10 seeds, ordered by age
     */
    public Page<Seed> allSeedsByAge(Integer page, Boolean asc){
        PageRequest pr = PageRequest.of(page, 10);
        if(asc) return seedRepository.findAll(pr.withSort(Sort.Direction.ASC, "dateOfStoring"));
        else return seedRepository.findAll(pr.withSort(Sort.Direction.DESC, "dateOfStoring"));
    }

    /**
     *
     * @param type
     * @param dateOfStoring
     * @param count
     * @return Returns the newly created seed
     */
    public Seed createSeed(String type, LocalDate dateOfStoring, Integer count){
        Seed seed = seedRepository.insert(new Seed(type,dateOfStoring,count));

        return seed;
    }

    /**
     * Removes a seed by its id
     * @param id
     */
    public void removeSeed(ObjectId id) {
        seedRepository.deleteById(id);
    }

    /**
     * Every day at midnight all seeds will be checked for age update
     */
    @Scheduled(cron = "0 0 12 * * *")
    public void updateDates(){
        seedRepository.findAll().forEach(seed -> {
            seed.setAge(seed.getAge());
            seedRepository.save(seed); // Updates seed
        });
    }
}
