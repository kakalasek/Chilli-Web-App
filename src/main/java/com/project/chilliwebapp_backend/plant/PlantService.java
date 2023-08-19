package com.project.chilliwebapp_backend.plant;

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
public class PlantService {

    @Autowired
    private PlantRepository plantRepository;

    public Page<Plant> allPlants(Integer page){
        PageRequest pr = PageRequest.of(page, 10);
        return plantRepository.findAllByDateOfDisposalIsNull(pr);
    }

    public Plant createPlant(String type, LocalDate dateOfPlanting, Integer count){
       Plant plant = plantRepository.insert(new Plant(type,dateOfPlanting,count));

       return plant;
    }

    public Page<Plant> allPlantsSorted(Integer page, String byWhat, Boolean asc){
        PageRequest pr = PageRequest.of(page, 10);
        if(asc) return plantRepository.findAllByDateOfDisposalIsNull(pr.withSort(Sort.Direction.ASC, byWhat));
        else return plantRepository.findAllByDateOfDisposalIsNull(pr.withSort(Sort.Direction.DESC, byWhat));
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
            plant.setGermination(plant.getGermination());

            plant.setDayOfFirstFruit(plant.getDayOfFirstFruit());
            plant.setDayOfFirstHarvestedFruit(plant.getDayOfFirstHarvestedFruit());
            plant.setDayOfDisposal(plant.getDayOfDisposal());

            return plantRepository.save(plant);
        });
    }
}
