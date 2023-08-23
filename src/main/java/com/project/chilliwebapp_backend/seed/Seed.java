package com.project.chilliwebapp_backend.seed;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.Period;

@Document(collection = "seeds") //defines a collection this object should be mapped to
@Data // Setters and Getters
@AllArgsConstructor // Self-explanatory
@NoArgsConstructor // Self-explanatory
/**
 * A seed object. Used as an entry to the 'seeds' collection
 */
public class Seed {
    @Id // So it would be generated for me
    @JsonSerialize(using = ToStringSerializer.class) // Serializes the id to the right format (hexadecimal string). Without this line, it would be sent as a timestamp
    private ObjectId id;

    private String type;
    private LocalDate dateOfStoring;
    private Integer count;
    private Integer age; // Increments itself every year

    public Seed(String type, LocalDate dateOfStoring, Integer count) {
        setType(type);
        setDateOfStoring(dateOfStoring);
        setCount(count);
        setAge(getAge());
    }


    public Integer getAge(){
        return Period.between(dateOfStoring, LocalDate.now()).getYears(); // Calculating the age as years between the date of storing and current date
    }
}
