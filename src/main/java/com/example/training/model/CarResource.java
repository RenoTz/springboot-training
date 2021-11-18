package com.example.training.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CarResource {

@NotBlank
private String matriculation;

@NotBlank
private String model;

@NotBlank
private String brand;

}
