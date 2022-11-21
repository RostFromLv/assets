package com.assets.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import com.assets.AbstractTest;
import com.assets.domain.Car;
import com.assets.mapper.CarMapper;
import com.example.commondtos.models.FirstDto;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.repository.JpaRepository;

@ExtendWith(MockitoExtension.class)
@SuppressWarnings(value = {"argument","initialization.field.uninitialized"}) // For testing cases
public class CarServiceUT extends AbstractTest {
  @Mock
  private CarMapper carMapper;
  @Mock
  private JpaRepository<Car, Long> repository;
  @InjectMocks
  private CarServiceImpl carService;
  //create
  @Test
  void createByCorrectData_ShouldReturn_CreatedCar() {
    when(carMapper.toEntity(firstDto)).thenReturn(carEntity);
    when(repository.save(carEntity)).thenReturn(carEntity);
    when(carMapper.toDto(carEntity)).thenReturn(firstDto);

    FirstDto actual = carService.create(firstDto);
    assertNotNull(actual);
    assertEquals(actual, firstDto);
  }
  @Test
  void createWithNullableId_Should_CorrectlyCreateCar() {
    FirstDto firstDtoWithNullId = getCarDtoWithNullId();
    Car carEntityWithNullId = getCarWithNullId();

    when(carMapper.toEntity(firstDtoWithNullId)).thenReturn(carEntityWithNullId);
    when(repository.save(carEntityWithNullId)).thenReturn(carEntity);
    when(carMapper.toDto(carEntity)).thenReturn(firstDto);

    FirstDto actual = carService.create(firstDtoWithNullId);
    assertNotNull(actual);
    assertEquals(actual, firstDto);
  }
  @Test
  void createWithNullDto_ShouldThrow_IllegalArgumentException() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> carService.create(null));
  }
  //createAll
  @Test
  void createAllByCorrectData_ShouldReturn_CollectionWithCreatedDto() {

    when(carMapper.toEntity(firstDto)).thenReturn(carEntity);
    when(repository.save(carEntity)).thenReturn(carEntity);
    when(carMapper.toDto(carEntity)).thenReturn(firstDto);

    when(carMapper.toEntity(secondFirstDto)).thenReturn(secondCarEntity);
    when(repository.save(secondCarEntity)).thenReturn(secondCarEntity);
    when(carMapper.toDto(secondCarEntity)).thenReturn(secondFirstDto);

    Collection<FirstDto> actual = carService.createAll(getCollectionOfCarDto());
    assertNotNull(actual);
    assertEquals(getCollectionOfCarDto(), actual);

  }
  @Test
  void createAllByNullArgument_ShouldThrow_IllegalArgumentException() {
    Collection<FirstDto> collectionWithNullArgument = new HashSet<>();
    collectionWithNullArgument.add(firstDto);
    collectionWithNullArgument.add(null);
    assertThrows(IllegalArgumentException.class,
        () -> carService.createAll(collectionWithNullArgument));
  }
  //update
  @Test
  void updateWithCorrectData_ShouldReturn_UpdatedDTO() {
    when(repository.findById(id)).thenReturn(Optional.of(carEntity));
    carEntity.setBodyType(secondBodyType);

    when(repository.save(carEntity)).thenReturn(carEntity);
    firstDto.setBodyType(secondBodyType);

    when(carMapper.toDto(carEntity)).thenReturn(firstDto);

    FirstDto actual = carService.update(firstDto, firstDto.getId());
    assertNotNull(actual);
    assertEquals(firstDto, actual);
  }
  @Test
  void updateWithCorrectData_ShouldReturn_DTOWithSameId() {
    Long idBeforeUpdate = id;
    when(repository.findById(idBeforeUpdate)).thenReturn(Optional.of(carEntity));
    carEntity.setBodyType(secondBodyType);

    when(repository.save(carEntity)).thenReturn(carEntity);
    firstDto.setBodyType(secondBodyType);

    when(carMapper.toDto(carEntity)).thenReturn(firstDto);

    FirstDto actual = carService.update(firstDto, firstDto.getId());

    assertNotNull(actual);
    assertEquals(actual.getId(), idBeforeUpdate);
  }
  @Test
  void updateWithNullField_ShouldThrow_IllegalArgumentException() {
    assertThrows(NullPointerException.class, () -> carService.update(getCarDtoWithNullBrand(),
        firstDto.getId()));
  }
  @Test
  void updateWithMissingId_ShouldThrow_EntityNotFoundException() {
      Long missId = 2L;
      when(repository.findById(missId)).thenThrow(EntityNotFoundException.class);
      assertThrows(EntityNotFoundException.class,()-> carService.update(firstDto,missId));
  }
  @Test
  void updateWithNullId_ShouldThrow_IllegalArgumentException() {
      assertThrows(IllegalArgumentException.class,()-> carService.update(firstDto,getCarDtoWithNullId().getId()));
  }
  //found
  @Test
  void findById_ShouldReturn_OptionalWithExistDto(){
    when(carMapper.toDto(carEntity)).thenReturn(firstDto);
    when(repository.findById(id)).thenReturn(Optional.of(carEntity));
    FirstDto actual = carService.findById(id);
    assertNotNull(actual);
    assertEquals(firstDto,actual);
  }
  @Test
  void findByWrongId_ShouldReturn_EmptyOptional(){
    Long absentId = 2L;
    when(repository.findById(absentId)).thenThrow(EntityNotFoundException.class);
    assertThrows(EntityNotFoundException.class,()->carService.findById(absentId));
  }
  @Test
  void findByNullId_ShouldThrow_IllegalArgumentException(){
    assertThrows(IllegalArgumentException.class,()-> carService.findById(null));
  }
  //findAll
  @Test
  void findAll_ShouldReturn_CollectionWithCarDto(){
      when(repository.findAll()).thenReturn(new ArrayList<>(getCollectionOfCarEntity()));
      when(carMapper.toDto(carEntity)).thenReturn(firstDto);
      when(carMapper.toDto(secondCarEntity)).thenReturn(secondFirstDto);

      Collection<FirstDto> actual = carService.findAll();
      assertNotNull(actual);
      assertTrue(actual.size()>1);
  }
  @Test
  void  findAll_ShouldReturn_EmptyCollection(){
      when(repository.findAll()).thenReturn(new ArrayList<>());

      Collection<FirstDto> actual = carService.findAll();
      assertNotNull(actual);
      assertTrue(actual.isEmpty());

  }
  //delete
  @Test
  void deleteById_ShouldVerifyCall(){
    carService.deleteById(id);
    Mockito.verify(repository,times(1)).deleteById(id);
  }
  @Test
  void deleteAll_ShouldVerifyCall(){
    carService.deleteAll();
    Mockito.verify(repository,times(1)).deleteAll();
  }
  //exist
  @Test
  void existByExistId_ShouldReturn_True(){
    when(repository.existsById(id)).thenReturn(true);
    assertTrue(carService.existById(id));
  }
  @Test
  void existByWrongId_ShouldReturn_False(){
    when(repository.existsById(id)).thenReturn(false);
    assertFalse(carService.existById(id));
  }
}