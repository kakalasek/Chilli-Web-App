package com.project.chilliwebapp_backend.plant;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000") // So my front end can receive data
@RequestMapping("/api/v1/plants") // URL this rest controller will be mapped to
/**
 * Receives requests and passes them to the service layer
 */
public class PlantController {

    @Autowired // Automatically injects the property
    private PlantService plantService;

    @GetMapping("{page}")
    /**
     * Returns a page of plants, unordered
     */
    public ResponseEntity<Page<Plant>> getAllPlants(@PathVariable Integer page){
        return new ResponseEntity<>(plantService.allPlants(page), HttpStatus.OK);
    }

    @GetMapping("{page}/{byWhat}/{asc}")
    /**
     * Returns a page of plants, ordered by a specific attribute
     */
    public ResponseEntity<Page<Plant>> getAllPlantsSorted(@PathVariable Integer page, @PathVariable String byWhat, @PathVariable Boolean asc){
        return new ResponseEntity<>(plantService.allPlantsSorted(page, byWhat, asc), HttpStatus.OK);
    }

    @PostMapping
    /**
     * Creates a new plant
     */
    public ResponseEntity<Plant> createPlant(@RequestBody Plant plant){
        return new ResponseEntity<>(plantService.createPlant(plant.getType(), plant.getDateOfPlanting(), plant.getCount()), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    /**
     * Updates a plant
     */
    public ResponseEntity<Optional<Plant>> updatePlant(@RequestBody Plant plant, @PathVariable ObjectId id){
        return new ResponseEntity<>(plantService.updatePlant(plant.getSprouted(), plant.getDateOfFirstFruit(), plant.getDateOfFirstHarvestedFruit(), plant.getDateOfDisposal(), id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    /**
     * Deletes a plant
     */
    public int deletePlant(@PathVariable ObjectId id){
        plantService.removePlant(id);
        return 200; // OK status
    }
}
