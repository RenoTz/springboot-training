package com.example.training.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CarResource {

@NotBlank
private String matriculation;

@NotBlank
private String model;

@NotBlank
private String brand;

}
