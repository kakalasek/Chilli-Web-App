package com.project.chilliwebapp_backend.archive;

import com.project.chilliwebapp_backend.plant.Plant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/archive")
public class ArchiveController {

    @Autowired
    ArchiveService archiveService;

    @GetMapping("{page}")
    public ResponseEntity<Page<Plant>> getAllArchivedPlants(@PathVariable Integer page){
        return new ResponseEntity<>(archiveService.allArchivedPlants(page), HttpStatus.OK);
    }

    @GetMapping("{page}/{byWhat}/{asc}")
    public ResponseEntity<Page<Plant>> getAllArchivedPlantsSorted(@PathVariable Integer page, @PathVariable String byWhat, @PathVariable Boolean asc){
        return new ResponseEntity<>(archiveService.allArchivedPlantsSorted(page, byWhat, asc), HttpStatus.OK);
    }
}
