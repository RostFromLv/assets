package com.assets.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import com.assets.AbstractTest;
import com.assets.commondb.domain.First;
import com.assets.commondtos.models.FirstDto;
import com.assets.mapper.FirstMapper;
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
public class FirstServiceUT extends AbstractTest {
  @Mock
  private FirstMapper firstMapper;
  @Mock
  private JpaRepository<First, Long> repository;
  @InjectMocks
  private FirstServiceImpl firstService;

  //create
  @Test
  void createByCorrectData_ShouldReturn_CreatedCar() {
    when(firstMapper.toEntity(firstDto)).thenReturn(firstEntity);
    when(repository.save(firstEntity)).thenReturn(firstEntity);
    when(firstMapper.toDto(firstEntity)).thenReturn(firstDto);

    FirstDto actual = firstService.create(firstDto);
    assertNotNull(actual);
    assertEquals(actual, firstDto);
  }
  @Test
  void createWithNullableId_Should_CorrectlyCreateCar() {
    FirstDto firstDtoWithNullId = getFirstDtoWithNullId();
    First carEntityWithNullId = getFirstWithNullId();

    when(firstMapper.toEntity(firstDtoWithNullId)).thenReturn(carEntityWithNullId);
    when(repository.save(carEntityWithNullId)).thenReturn(firstEntity);
    when(firstMapper.toDto(firstEntity)).thenReturn(firstDto);

    FirstDto actual = firstService.create(firstDtoWithNullId);
    assertNotNull(actual);
    assertEquals(actual, firstDto);
  }
  @Test
  void createWithNullDto_ShouldThrow_IllegalArgumentException() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> firstService.create(null));
  }
  //createAll
  @Test
  void createAllByCorrectData_ShouldReturn_CollectionWithCreatedDto() {

    when(firstMapper.toEntity(firstDto)).thenReturn(firstEntity);
    when(repository.save(firstEntity)).thenReturn(firstEntity);
    when(firstMapper.toDto(firstEntity)).thenReturn(firstDto);

    when(firstMapper.toEntity(secondFirstDto)).thenReturn(secondFirstEntity);
    when(repository.save(secondFirstEntity)).thenReturn(secondFirstEntity);
    when(firstMapper.toDto(secondFirstEntity)).thenReturn(secondFirstDto);

    Collection<FirstDto> actual = firstService.createAll(getCollectionOfFirstDto());
    assertNotNull(actual);
    assertEquals(getCollectionOfFirstDto(), actual);

  }
  @Test
  void createAllByNullArgument_ShouldThrow_IllegalArgumentException() {
    Collection<FirstDto> collectionWithNullArgument = new HashSet<>();
    collectionWithNullArgument.add(firstDto);
    collectionWithNullArgument.add(null);
    assertThrows(IllegalArgumentException.class,
        () -> firstService.createAll(collectionWithNullArgument));
  }
  //update
  @Test
  void updateWithCorrectData_ShouldReturn_UpdatedDTO() {
    when(repository.findById(id)).thenReturn(Optional.of(firstEntity));
    firstEntity.setBodyType(secondBodyType);

    when(repository.save(firstEntity)).thenReturn(firstEntity);
    firstDto.setBodyType(secondBodyType);

    when(firstMapper.toDto(firstEntity)).thenReturn(firstDto);

    FirstDto actual = firstService.update(firstDto, firstDto.getId());
    assertNotNull(actual);
    assertEquals(firstDto, actual);
  }
  @Test
  void updateWithCorrectData_ShouldReturn_DTOWithSameId() {
    Long idBeforeUpdate = id;
    when(repository.findById(idBeforeUpdate)).thenReturn(Optional.of(firstEntity));
    firstEntity.setBodyType(secondBodyType);

    when(repository.save(firstEntity)).thenReturn(firstEntity);
    firstDto.setBodyType(secondBodyType);

    when(firstMapper.toDto(firstEntity)).thenReturn(firstDto);

    FirstDto actual = firstService.update(firstDto, firstDto.getId());

    assertNotNull(actual);
    assertEquals(actual.getId(), idBeforeUpdate);
  }
  @Test
  void updateWithNullField_ShouldThrow_IllegalArgumentException() {
    assertThrows(NullPointerException.class, () -> firstService.update(getFirstDtoWithNullBrand(),
        firstDto.getId()));
  }
  @Test
  void updateWithMissingId_ShouldThrow_EntityNotFoundException() {
    Long missId = 2L;
    when(repository.findById(missId)).thenThrow(EntityNotFoundException.class);
    assertThrows(EntityNotFoundException.class,()-> firstService.update(firstDto,missId));
  }
  @Test
  void updateWithNullId_ShouldThrow_IllegalArgumentException() {
    assertThrows(IllegalArgumentException.class,()-> firstService.update(firstDto,getFirstDtoWithNullId().getId()));
  }
  //found
  @Test
  void findById_ShouldReturn_OptionalWithExistDto(){
    when(firstMapper.toDto(firstEntity)).thenReturn(firstDto);
    when(repository.findById(id)).thenReturn(Optional.of(firstEntity));
    FirstDto actual = firstService.findById(id);
    assertNotNull(actual);
    assertEquals(firstDto,actual);
  }
  @Test
  void findByWrongId_ShouldReturn_EmptyOptional(){
    Long absentId = 2L;
    when(repository.findById(absentId)).thenThrow(EntityNotFoundException.class);
    assertThrows(EntityNotFoundException.class,()-> firstService.findById(absentId));
  }
  @Test
  void findByNullId_ShouldThrow_IllegalArgumentException(){
    assertThrows(IllegalArgumentException.class,()-> firstService.findById(null));
  }
  //findAll
  @Test
  void findAll_ShouldReturn_CollectionWithCarDto(){
    when(repository.findAll()).thenReturn(new ArrayList<>(getCollectionOfFirstEntity()));
    when(firstMapper.toDto(firstEntity)).thenReturn(firstDto);
    when(firstMapper.toDto(secondFirstEntity)).thenReturn(secondFirstDto);

    Collection<FirstDto> actual = firstService.findAll();
    assertNotNull(actual);
    assertTrue(actual.size()>1);
  }
  @Test
  void  findAll_ShouldReturn_EmptyCollection(){
    when(repository.findAll()).thenReturn(new ArrayList<>());

    Collection<FirstDto> actual = firstService.findAll();
    assertNotNull(actual);
    assertTrue(actual.isEmpty());

  }
  //delete
  @Test
  void deleteById_ShouldVerifyCall(){
    firstService.deleteById(id);
    Mockito.verify(repository,times(1)).deleteById(id);
  }
  @Test
  void deleteAll_ShouldVerifyCall(){
    firstService.deleteAll();
    Mockito.verify(repository,times(1)).deleteAll();
  }
  //exist
  @Test
  void existByExistId_ShouldReturn_True(){
    when(repository.existsById(id)).thenReturn(true);
    assertTrue(firstService.existById(id));
  }
  @Test
  void existByWrongId_ShouldReturn_False(){
    when(repository.existsById(id)).thenReturn(false);
    assertFalse(firstService.existById(id));
  }
}