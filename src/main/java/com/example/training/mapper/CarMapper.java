package com.example.training.mapper;

import com.example.training.model.CarEntity;
import com.example.training.model.CarResource;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CarMapper {

      CarResource carEntityToCarResource(CarEntity carEntity);

      CarEntity carResourceToCarEntity(CarResource carResource);

}
