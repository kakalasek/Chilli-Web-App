package com.project.chilliwebapp_backend.plant;

import lombok.NoArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import static java.time.Month.*;

@Configuration
public class PlantConfig {

    @Bean
    CommandLineRunner commandLineRunnerPlant(PlantRepository repository){
        return args -> {
            Plant plant1 = new Plant(
                    "Habanero",
                    LocalDate.of(2022, FEBRUARY, 23),
                    LocalDate.of(2022, MAY, 12),
                    LocalDate.of(2022, SEPTEMBER, 25),
                    LocalDate.of(2022, NOVEMBER, 3),
                    10,
                    4
            );

            repository.save(plant1);
        };
    }
}
