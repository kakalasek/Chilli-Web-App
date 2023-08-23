package com.project.chilliwebapp_backend.plant;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Document(collection = "plants") //defines a collection this object should be mapped to
@Data // Setters and Getters
@AllArgsConstructor // Self-explanatory
@NoArgsConstructor // Self-explanatory
/**
 * A plant object. Used as an entry to the 'plants' collection
 */
public class Plant {

    @Id // So it would be generated for me
    @JsonSerialize(using = ToStringSerializer.class) // Serializes the id to the right format (hexadecimal string). Without this line, it would be sent as a timestamp
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

        setDayFromPlanting(getDayFromPlanting());
        setGermination((double)sprouted/(((double)count/100)));
    }

    /**
     * Calculates the day from planting as days between the date of planting and now
     * @return
     */
    public Integer getDayFromPlanting(){
        if(dateOfDisposal != null){ // If date of disposal is null, the plant is archived, so we no longer want to update the day from planting
            return null;
        }
        return (int)dateOfPlanting.until(LocalDate.now(), ChronoUnit.DAYS);
    }

    /**
     * Calculates the day of first fruit as days between the date of first fruit and now
     * @return
     */
    public Integer getDayOfFirstFruit(){
        if(dateOfFirstFruit == null) return null;
        return (int)dateOfPlanting.until(dateOfFirstFruit, ChronoUnit.DAYS);
    }

    /**
     * Calculates the day of first harvested fruit as days between the date of first harvested fruit and now
     * @return
     */
    public Integer getDayOfFirstHarvestedFruit(){
        if(dateOfFirstHarvestedFruit == null) return null;
        return (int)dateOfPlanting.until(dateOfFirstHarvestedFruit, ChronoUnit.DAYS);
    }

    /**
     * Calculates the day of disposal as days between the date of disposal and now
     * @return
     */
    public Integer getDayOfDisposal(){
        if(dateOfDisposal == null) return null ;
        return (int)dateOfPlanting.until(dateOfDisposal, ChronoUnit.DAYS);
    }

    /**
     * Calculates germination from sprouted and count
     * @return
     */
    public Double getGermination(){
        return ((double)sprouted/(((double)count/100)));
    }
}
