package com.assets.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.assets.commondb.domain.First;
import com.assets.commondtos.models.FirstDto;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FirstMapperTest extends AbstractMapperTest {

  private final FirstMapper firstMapper;

  //to Entity
  @Test
  void carDtoShouldMapToCarEntity() {
    First mappedCar = firstMapper.toEntity(firstDto);
    assertNotNull(mappedCar);
    assertEquals(mappedCar, firstEntity);
  }

  @Test
  void carDtoMapToCarEntityWithNullParamShouldThrowNPE() {
    assertThrows(NullPointerException.class,
        () -> firstMapper.toEntity(getFirstDtoWithNullBrand()));
  }

  //To DToF
  @Test
  void carEntityShouldMapToCarDto() {
    FirstDto mappedFirstDto = firstMapper.toDto(firstEntity);
    assertNotNull(mappedFirstDto);
    assertEquals(mappedFirstDto, firstDto);
  }

  @Test
  void carEntityMapToCarDtoWithNullParamShouldThrowNPE() {
    assertThrows(NullPointerException.class, () -> firstMapper.toDto(getFirstWithNullBrand()));
  }

  //Update
  @Test
  void carDtoUpdateShouldMapToCarEntity_WithChangedData() {
    String brandToChange = "Toyota";
    firstDto.setBrand(brandToChange);
    firstMapper.updateProperties(firstDto, firstEntity);
    assertNotNull(firstEntity);
    assertEquals(firstEntity.getBrand(), brandToChange);
  }

  @Test
  void carDtoShouldIgnoreIdFieldWhileUpdate() {
    String brandToChange = "Toyota";
    firstDto.setBrand(brandToChange);
    firstDto.setId(100L);
    firstMapper.updateProperties(firstDto, firstEntity);
    assertNotNull(firstEntity);
    assertEquals(firstEntity.getId(), id);
  }

  @Test
  void updateCarDtoMapToCarEntityWithNullParamShouldThrowNPE() {
    assertThrows(NullPointerException.class,
        () -> firstMapper.updateProperties(getFirstDtoWithNullBrand(), firstEntity));
  }
}