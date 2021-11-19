package com.example.training.services;

import com.example.training.mapper.CarMapper;
import com.example.training.model.CarResource;
import com.example.training.repositories.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarService {

  private final CarRepository repository;
  private final CarMapper mapper;

  /**
   * Retrieve a user by username
   *
   * @param matriculation matriculation
   * @return a {@link CarResource} founded
   */
  public CarResource getCar(String matriculation) {
    return mapper.carEntityToCarResource(repository.findOneByMatriculation(matriculation));
  }

  /**
   * Register a user
   *
   * @param car a {@link CarResource} instance
   * @return a {@link CarResource} registered instance
   * @throws Exception if user already exists in database
   */
  public CarResource create(CarResource car) throws Exception {
    return mapper.carEntityToCarResource(repository.create(mapper.carResourceToCarEntity(car)));
  }

}
