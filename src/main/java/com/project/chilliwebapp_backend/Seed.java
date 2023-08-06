package com.project.chilliwebapp_backend;

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
    private ObjectId id;

    private String type;
    private LocalDate dayOfStoring;
    private Integer count;
    @Transient
    private Integer age;

    public Seed(String type, LocalDate dayOfStoring, Integer count) {
        this.type = type;
        this.dayOfStoring = dayOfStoring;
        this.count = count;
    }

    public Integer getAge(){
        return Period.between(dayOfStoring, LocalDate.now()).getYears();
    }
}
