package com.project.chilliwebapp_backend.plant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

@Document(collection = "plants")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Plant {

    @Id
    private ObjectId id;

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
    private Double germination;


    public Plant(String type, LocalDate dateOfPlanting, LocalDate dateOfFirstFruit, LocalDate dateOfFirstHarvestedFruit, LocalDate dateOfDisposal, Integer count, Integer sprouted) {
        this.type = type;
        this.dateOfPlanting = dateOfPlanting;
        this.dateOfFirstFruit = dateOfFirstFruit;
        this.dateOfFirstHarvestedFruit = dateOfFirstHarvestedFruit;
        this.dateOfDisposal = dateOfDisposal;
        this.count = count;
        this.sprouted = sprouted;
    }

    public Integer getDayFromPlanting(){
        return (int)dateOfPlanting.until(LocalDate.now(), ChronoUnit.DAYS);
    }

    public Integer getDayOfFirstFruit(){
        return (int)dateOfPlanting.until(dateOfFirstFruit, ChronoUnit.DAYS);
    }

    public Integer getDayOfFirstHarvestedFruit(){
        return (int)dateOfPlanting.until(dateOfFirstHarvestedFruit, ChronoUnit.DAYS);
    }

    public Integer getDayOfDisposal(){
        return (int)dateOfPlanting.until(dateOfDisposal, ChronoUnit.DAYS);
    }

    public Double getGermination(){
        return (double)sprouted/((double)count/100);
    }
}
