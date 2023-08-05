package com.project.chilliwebapp_backend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "plants")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Plant {

    private String type;
    private LocalDate dateOfPlanting;
    private Integer dayFromPlanting;
    private LocalDate dateOfFirstFruit;
    private Integer dayOfFirstFruit;
    private LocalDate dateOfFirstHarvestedFruit;
    private Integer dayOfFirstHarvestedFruit;
    private LocalDate dateOfDisposal;
    private Integer dayOfDisposal;
    private Integer count;
    private Integer sprouted;
    private Integer germination;

}
