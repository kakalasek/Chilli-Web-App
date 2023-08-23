package com.project.chilliwebapp_backend.archive;

import com.project.chilliwebapp_backend.plant.Plant;
import com.project.chilliwebapp_backend.plant.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
/**
 * Communicates with the repository and handles all internal logic of the request
 */
public class ArchiveService {

    @Autowired // Automatically injects the property
    PlantRepository plantRepository;

    /**
     *
     * @param page Page number
     * @return Page of 10 plants with date of disposal, unordered
     */
    public Page<Plant> allArchivedPlants(Integer page) {
        PageRequest pr = PageRequest.of(page, 10);
        return plantRepository.findAllByDateOfDisposalIsNotNull(pr);
    }

    /**
     *
     * @param page Page Number
     * @param byWhat Attribute to sort by
     * @param asc True=ASC/False=DESC
     * @return Page of 10 plants with date of disposal, ordered by a specific attribute
     */
    public Page<Plant> allArchivedPlantsSorted(Integer page, String byWhat, Boolean asc) {
        PageRequest pr = PageRequest.of(page, 10);
        if(asc) return plantRepository.findAllByDateOfDisposalIsNotNull(pr.withSort(Sort.Direction.ASC, byWhat));
        else return plantRepository.findAllByDateOfDisposalIsNotNull(pr.withSort(Sort.Direction.DESC, byWhat));
    }
}
