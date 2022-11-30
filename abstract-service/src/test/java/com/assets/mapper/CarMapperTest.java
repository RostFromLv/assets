package com.assets.mapper;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.assets.domain.Car;
import com.assets.commondtos.models.FirstDto;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CarMapperTest extends AbstractMapperTest {

  private final CarMapper carMapper;

  //to Entity
  @Test
  void carDtoShouldMapToCarEntity() {
    Car mappedCar = carMapper.toEntity(firstDto);
    assertNotNull(mappedCar);
    assertEquals(mappedCar, carEntity);
  }

  @Test
  void carDtoMapToCarEntityWithNullParamShouldThrowNPE(){
    assertThrows(NullPointerException.class,()->carMapper.toEntity(getCarDtoWithNullBrand()));
  }

  //To DTo
  @Test
  void carEntityShouldMapToCarDto() {
    FirstDto mappedFirstDto = carMapper.toDto(carEntity);
    assertNotNull(mappedFirstDto);
    assertEquals(mappedFirstDto, firstDto);
  }

  @Test
  void carEntityMapToCarDtoWithNullParamShouldThrowNPE(){
    assertThrows(NullPointerException.class,()->carMapper.toDto(getCarWithNullBrand()));
  }

  //Update
  @Test
  void carDtoUpdateShouldMapToCarEntity_WithChangedData() {
    String brandToChange = "Toyota";
    firstDto.setBrand(brandToChange);
    carMapper.updateProperties(firstDto, carEntity);
    assertNotNull(carEntity);
    assertEquals(carEntity.getBrand(),brandToChange);
  }

  @Test
  void carDtoShouldIgnoreIdFieldWhileUpdate(){
    String brandToChange ="Toyota";
    firstDto.setBrand(brandToChange);
    firstDto.setId(100L);
    carMapper.updateProperties(firstDto, carEntity);
    assertNotNull(carEntity);
    assertEquals(carEntity.getId(),id);
  }

  @Test
  void updateCarDtoMapToCarEntityWithNullParamShouldThrowNPE(){
    assertThrows(NullPointerException.class,()->carMapper.updateProperties(getCarDtoWithNullBrand(),
        carEntity));
  }
}
