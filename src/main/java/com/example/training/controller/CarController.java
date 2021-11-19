package com.example.training.controller;

import com.example.training.model.CarResource;
import com.example.training.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {

  private final CarService carService;

  /**
   * Retrieve car by matriculation
   *
   * @param matriculation id
   * @return user
   */
  @GetMapping("/{matriculation}")
  public ResponseEntity<CarResource> getUser(@PathVariable("matriculation") String matriculation) {
    return Optional.ofNullable(carService.getCar(matriculation))
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  /**
   * Register a user. Throw an AlreadyExistException if already exists in database.
   *
   * @param car a {@link CarResource} to be registered
   * @return a {@link CarResource} registered
   */
  @PostMapping
  public ResponseEntity<Object> create(@RequestBody @Validated CarResource car) {
    try {
      return new ResponseEntity<>(carService.create(car), HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

}
