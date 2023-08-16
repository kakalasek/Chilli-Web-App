package com.project.chilliwebapp_backend.plant;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/plants")
public class PlantController {

    @Autowired
    private PlantService plantService;

    @GetMapping
    public ResponseEntity<List<Plant>> getAllPlants(){
        return new ResponseEntity<>(plantService.allPlants(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Plant> createPlant(@RequestBody Plant plant){
        return new ResponseEntity<>(plantService.createPlant(plant.getType(), plant.getDateOfPlanting(), plant.getCount()), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Optional<Plant>> updatePlant(@RequestBody Plant plant, @PathVariable ObjectId id){
        return new ResponseEntity<>(plantService.updatePlant(plant.getSprouted(), plant.getDateOfFirstFruit(), plant.getDateOfFirstHarvestedFruit(), plant.getDateOfDisposal(), id), HttpStatus.OK);
    }

    @DeleteMapping(path = {"{id}"})
    public int deletePlant(@PathVariable("id") ObjectId id){
        plantService.removePlant(id);
        return 200;
    }
}
