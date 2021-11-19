package com.example.training.repositories;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.example.training.errors.AlreadyExistsException;
import com.example.training.model.CarEntity;

import java.util.Objects;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CarRepository {

  private final MongoTemplate mongoTemplate;

  /**
   * Retrieve a user by username
   *
   * @param matriculation username
   * @return {@link CarEntity} instance
   */
  public CarEntity findOneByMatriculation(String matriculation) {
    Query query = new Query();
    query.addCriteria(Criteria.where("matriculation").is(matriculation));
    log.info("Attempting to find car with matriculation : {}", matriculation);
    CarEntity carEntity = mongoTemplate.findOne(query, CarEntity.class);
    if (Objects.nonNull(carEntity)) {
      log.info("Car found : {} - {} - {}", carEntity.getMatriculation(), carEntity.getBrand(),
          carEntity.getModel());
    } else {
      log.info("No car found with the matriculation : {}", matriculation);
    }
    return carEntity;
  }

  /**
   * Register a user
   *
   * @param car a {@link CarEntity} instance
   * @return {@link CarEntity} registered instance
   * @throws Exception if user already exists in database
   */
  public CarEntity create(CarEntity car) throws AlreadyExistsException {
    CarEntity newCar;
    log.info("Attempting to create car");
    if (Objects.isNull(findOneByMatriculation(car.getMatriculation()))) {
      newCar = mongoTemplate.insert(car);
      log.info("Car created : {} - {} - {}", car.getMatriculation(), car.getBrand(),
          car.getModel());
    } else {
      log.error("Car already exists in database with matriculation : {}", car.getMatriculation());
      throw new AlreadyExistsException("Car already exists in database");
    }
    return newCar;
  }

  /**
   * Register a user
   *
   * @param car a {@link CarEntity} instance
   * @return {@link CarEntity} registered instance
   * @throws Exception if user already exists in database
   */
  public CarEntity createOrUpdate(CarEntity car) throws AlreadyExistsException {
    CarEntity newCar;
    log.info("Attempting to create car");
    CarEntity carRegistered = findOneByMatriculation(car.getMatriculation());
    if (Objects.isNull(carRegistered)) {
      newCar = mongoTemplate.insert(car);
      log.info("Car created : {} - {} - {}", car.getMatriculation(), car.getBrand(),
          car.getModel());
    } else {
      Query query = new Query();
      query.addCriteria(Criteria.where("matriculation").is(car.getMatriculation()));
      Update update = new Update().set("brand", car.getBrand()).set("model", car.getModel());
      mongoTemplate.upsert(query, update, CarEntity.class);
      log.info("Car updated in database with matriculation : {}", car.getMatriculation());
      throw new AlreadyExistsException("Car already exists in database");
    }
    return newCar;
  }

}
