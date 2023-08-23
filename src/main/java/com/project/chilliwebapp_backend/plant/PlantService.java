package com.project.chilliwebapp_backend.plant;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Optional;

@Service
/**
 * Communicates with the repository and handles all internal logic of the request
 */
public class PlantService {

    @Autowired // Automatically injects the property
    private PlantRepository plantRepository;

    /**
     *
     * @param page Page number
     * @return Page of 10 plants without date of disposal, unordered
     */
    public Page<Plant> allPlants(Integer page){
        PageRequest pr = PageRequest.of(page, 10); // Each page is 10 items long
        return plantRepository.findAllByDateOfDisposalIsNull(pr);
    }

    /**
     *
     * @param type
     * @param dateOfPlanting
     * @param count
     * @return Returns the newly created plant
     */
    public Plant createPlant(String type, LocalDate dateOfPlanting, Integer count){
       Plant plant = plantRepository.insert(new Plant(type,dateOfPlanting,count));

       return plant;
    }

    /**
     *
     * @param page Page Number
     * @param byWhat Attribute to sort by
     * @param asc True=ASC/False=DESC
     * @return Page of 10 plants without date of disposal, ordered by a specific attribute
     */
    public Page<Plant> allPlantsSorted(Integer page, String byWhat, Boolean asc){
        PageRequest pr = PageRequest.of(page, 10);
        if(asc) return plantRepository.findAllByDateOfDisposalIsNull(pr.withSort(Sort.Direction.ASC, byWhat));
        else return plantRepository.findAllByDateOfDisposalIsNull(pr.withSort(Sort.Direction.DESC, byWhat));
    }

    /**
     * Removes a plant by its id
     * @param id
     */
    public void removePlant(ObjectId id) {
        plantRepository.deleteById(id);
    }

    /**
     * Updates a plant by its id
     * @param sprouted
     * @param dateOfFirstFruit
     * @param dateOfFirstHarvestedFruit
     * @param dateOfDisposal
     * @param id
     * @return
     */
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

    /**
     * Updates day from planting every midnight
     */
    @Scheduled(cron = "0 0 12 * * *")
    public void updateDates(){
        plantRepository.findAllByDateOfDisposalIsNull().forEach(plant -> {
            plant.setDayFromPlanting(plant.getDayFromPlanting());
            plantRepository.save(plant);
        });
    }
}
