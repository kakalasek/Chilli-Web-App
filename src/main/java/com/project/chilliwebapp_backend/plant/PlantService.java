package com.project.chilliwebapp_backend.plant;

import com.project.chilliwebapp_backend.plant.Plant;
import com.project.chilliwebapp_backend.plant.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PlantService {

    @Autowired
    private PlantRepository plantRepository;

    public List<Plant> allPlants(){
        return plantRepository.findAll();
    }

    public Plant createPlant(String type, LocalDate dateOfPlanting, LocalDate dateOfFirstFruit, LocalDate dateOfFirstHarvestedFruit, LocalDate dateOfDisposal, Integer count, Integer sprouted){
       Plant plant = plantRepository.insert(new Plant(type,dateOfPlanting,dateOfFirstFruit,dateOfFirstHarvestedFruit,dateOfDisposal,count,sprouted));

       return plant;
    }

}
