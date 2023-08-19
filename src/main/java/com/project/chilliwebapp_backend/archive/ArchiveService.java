package com.project.chilliwebapp_backend.archive;

import com.project.chilliwebapp_backend.plant.Plant;
import com.project.chilliwebapp_backend.plant.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ArchiveService {

    @Autowired
    PlantRepository plantRepository;

    public Page<Plant> allArchivedPlants(Integer page) {
        PageRequest pr = PageRequest.of(page, 10);
        return plantRepository.findAllByDateOfDisposalIsNotNull(pr);
    }

    public Page<Plant> allArchivedPlantsSorted(Integer page, String byWhat, Boolean asc) {
        PageRequest pr = PageRequest.of(page, 10);
        if(asc) return plantRepository.findAllByDateOfDisposalIsNotNull(pr.withSort(Sort.Direction.ASC, byWhat));
        else return plantRepository.findAllByDateOfDisposalIsNotNull(pr.withSort(Sort.Direction.DESC, byWhat));
    }
}
