package com.project.chilliwebapp_backend.plant;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PlantService {

    @Autowired
    private PlantRepository plantRepository;

    public List<Plant> allPlants(){
        return plantRepository.findAll();
    }

    public Plant createPlant(String type, LocalDate dateOfPlanting, Integer count){
       Plant plant = plantRepository.insert(new Plant(type,dateOfPlanting,count));

       return plant;
    }

    public void removePlant(ObjectId id) {
        plantRepository.deleteById(id);
    }

    public Optional<Plant> updatePlant(Integer sprouted, LocalDate dateOfFirstFruit, LocalDate dateOfFirstHarvestedFruit, LocalDate dateOfDisposal, ObjectId id) {
        return plantRepository.findById(id).map(plant -> {
            plant.setSprouted(sprouted);
            plant.setDateOfFirstFruit(dateOfFirstFruit);
            plant.setDateOfFirstHarvestedFruit(dateOfFirstHarvestedFruit);
            plant.setDateOfDisposal(dateOfDisposal);
            return plantRepository.save(plant);
        });
    }
}
