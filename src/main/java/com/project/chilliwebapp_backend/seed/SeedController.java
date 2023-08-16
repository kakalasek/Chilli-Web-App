package com.project.chilliwebapp_backend.seed;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.StyledEditorKit;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/seeds")
public class SeedController {

    @Autowired
    private SeedService seedService;

    @GetMapping
    public ResponseEntity<List<Seed>> getAllSeeds(){
        return new ResponseEntity<>(seedService.allSeeds(), HttpStatus.OK);
    }

    @GetMapping(path = "{asc}")
    public ResponseEntity<List<Seed>> getAllSeedsSortedByAge(@PathVariable("asc") Boolean asc){
        return new ResponseEntity<>(seedService.allSeedsByAge(asc), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Seed> createSeed(@RequestBody Seed seed){
        return new ResponseEntity<>(seedService.createSeed(seed.getType(), seed.getDateOfStoring(), seed.getCount()),HttpStatus.CREATED);
    }

   @DeleteMapping(path ="{id}")
   public int deleteSeed(@PathVariable("id") ObjectId id){
        seedService.removeSeed(id);
        return 200;
   }
}
