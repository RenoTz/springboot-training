package com.example.training.services;

import com.example.training.errors.AlreadyExistsException;
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
   * @throws AlreadyExistsException if user already exists in database
   */
  public CarResource create(CarResource car) throws AlreadyExistsException {
    return mapper.carEntityToCarResource(repository.create(mapper.carResourceToCarEntity(car)));
  }

  /**
   * Register or update a car
   *
   * @param car a {@link CarResource} instance
   * @return a {@link CarResource} registered instance
   * @throws AlreadyExistsException if user already exists in database
   */
  public CarResource createOrUpdate(CarResource car) throws AlreadyExistsException {
    return mapper.carEntityToCarResource(repository.createOrUpdate(mapper.carResourceToCarEntity(car)));
  }

}
