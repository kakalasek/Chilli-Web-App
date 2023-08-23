package com.project.chilliwebapp_backend.seed;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000") // So my front end can receive data
@RequestMapping("/api/v1/seeds") // URL this rest controller will be mapped to
/**
 * Receives requests and passes them to the service layer
 */
public class SeedController {

    @Autowired // Automatically injects the property
    private SeedService seedService;

    @GetMapping("{page}")
    /**
     * Returns a page of seeds, unordered
     */
    public ResponseEntity<Page<Seed>> getAllSeeds(@PathVariable Integer page){
        return new ResponseEntity<>(seedService.allSeeds(page), HttpStatus.OK);
    }

    @GetMapping("{page}/{asc}")
    /**
     * Returns a page of seeds, ordered by age
     */
    public ResponseEntity<Page<Seed>> getAllSeedsSortedByAge(@PathVariable Integer page, @PathVariable Boolean asc){
        return new ResponseEntity<>(seedService.allSeedsByAge(page, asc), HttpStatus.OK);
    }

    @PostMapping
    /**
     * Creates a new seed
     */
    public ResponseEntity<Seed> createSeed(@RequestBody Seed seed){
        return new ResponseEntity<>(seedService.createSeed(seed.getType(), seed.getDateOfStoring(), seed.getCount()),HttpStatus.CREATED);
    }

   @DeleteMapping("{id}")
   /**
    * Deletes a seed by its id
    */
   public int deleteSeed(@PathVariable ObjectId id){
        seedService.removeSeed(id);
        return 200; // OK status
   }
}
