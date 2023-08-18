package com.project.chilliwebapp_backend.plant;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
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
    @JsonSerialize(using = ToStringSerializer.class)
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

    public Plant(String type, LocalDate dateOfPlanting, Integer count) {
        setType(type);
        setDateOfPlanting(dateOfPlanting);
        setCount(count);
        setSprouted(0);

        setDayFromPlanting((int)dateOfPlanting.until(LocalDate.now(), ChronoUnit.DAYS));
        setGermination((double)sprouted/(((double)count/100)));
    }

    public Integer getDayFromPlanting(){
        return (int)dateOfPlanting.until(LocalDate.now(), ChronoUnit.DAYS);
    }

    public Integer getDayOfFirstFruit(){
        if(dateOfFirstFruit == null) return null;
        return (int)dateOfPlanting.until(dateOfFirstFruit, ChronoUnit.DAYS);
    }

    public Integer getDayOfFirstHarvestedFruit(){
        if(dateOfFirstHarvestedFruit == null) return null;
        return (int)dateOfPlanting.until(dateOfFirstHarvestedFruit, ChronoUnit.DAYS);
    }

    public Integer getDayOfDisposal(){
        if(dateOfDisposal == null) return null ;
        return (int)dateOfPlanting.until(dateOfDisposal, ChronoUnit.DAYS);
    }

    public Double getGermination(){
        return ((double)sprouted/(((double)count/100)));
    }
}
