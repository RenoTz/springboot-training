package com.example.training.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Car")
public class CarEntity {

    private String matriculation;

    private String model;

    private String brand;

}
