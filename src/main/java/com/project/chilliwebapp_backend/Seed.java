package com.project.chilliwebapp_backend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "seeds")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seed {
    @Id
    private ObjectId id;

    private String type;
    private LocalDate dayOfStoring;
    private Integer age;
    private Integer count;
}
