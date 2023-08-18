package com.project.chilliwebapp_backend.archive;

import com.project.chilliwebapp_backend.plant.Plant;
import com.project.chilliwebapp_backend.plant.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ArchiveService {

    @Autowired
    PlantRepository plantRepository;

    public Page<Plant> allArchivedPlants(Integer page) {
        PageRequest pr = PageRequest.of(page, 10);
        return plantRepository.findAllByDateOfDisposalIsNotNull(pr);
    }
}
