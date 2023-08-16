package com.project.chilliwebapp_backend.seed;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.Period;

@Document(collection = "seeds")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seed {
    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;

    private String type;
    private LocalDate dateOfStoring;
    private Integer count;
    private Integer age;

    public Seed(String type, LocalDate dateOfStoring, Integer count) {
        setType(type);
        setDateOfStoring(dateOfStoring);
        setCount(count);
        setAge(Period.between(dateOfStoring, LocalDate.now()).getYears());
    }

    public Integer getAge(){
        return Period.between(dateOfStoring, LocalDate.now()).getYears();
    }
}
