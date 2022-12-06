package com.assets.firstservice.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.assets.commondtos.models.FirstDto;
import com.assets.firstservice.AbstractTest;
import java.util.Collection;
import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@SuppressWarnings(value = "argument")
public class FirstServiceIT extends AbstractTest {

  private final FirstRepository firstRepository;
  private final FirstService firstService;

  @Autowired
  public FirstServiceIT(FirstRepository firstRepository, FirstService firstService) {
    this.firstRepository = firstRepository;
    this.firstService = firstService;
  }

  @BeforeEach
  void beforeEach(){
    firstRepository.deleteAll();
  }

  //create
  @Test
  void create_shouldCreateFirst_andReturn_createdFirstDto(){
     FirstDto createdFirstDto =  firstService.create(anotherFirstDto);

     assertNotNull(createdFirstDto);
     assertNotNull(createdFirstDto.getId());
     Assertions.assertTrue(firstRepository.existsById(createdFirstDto.getId()));
  }
  //createAll
  @Test
  void createAll_shouldCreateFirsts_andReturn_FirstCollection(){
    Assertions.assertTrue(firstService.findAll().isEmpty());
    Collection<FirstDto> collectionOfCreatedFirst = firstService.createAll(getCollectionOfFirstDto());
    assertFalse(collectionOfCreatedFirst.isEmpty());
    Assertions.assertFalse(firstService.findAll().isEmpty());
  }
  //update
  @Test
  void update_shouldUpdateFirst_AndReturnUpdatedFirstDto(){
    FirstDto createdFirstDto = firstService.create(anotherFirstDto);
    assertNotNull(createdFirstDto);
    firstDto.setId(createdFirstDto.getId());

    FirstDto updatedDto = firstService.update(firstDto, firstDto.getId());

    assertNotNull(updatedDto);

    org.assertj.core.api.Assertions.assertThat(firstDto)
        .usingRecursiveComparison()
        .ignoringFields("id","createdAt","updatedAt")
        .isEqualTo(updatedDto);

  }
  //findById
  @Test
  void  findById_ShouldReturn_existFirstDto(){
    FirstDto createdFirstDto = firstService.create(anotherFirstDto);
    assertNotNull(createdFirstDto);

    FirstDto foundFirstDto = firstService.findById(createdFirstDto.getId());
    assertNotNull(foundFirstDto);

    org.assertj.core.api.Assertions.assertThat(createdFirstDto)
        .usingRecursiveComparison()
        .ignoringFields("id","createdAt","updatedAt")
        .isEqualTo(foundFirstDto);
  }
  @Test
  void findById_ShouldThrow_EntityNotFoundException(){
    assertThrows(EntityNotFoundException.class,()-> firstService.findById(1L));
  }
  //findAll
  @Test
  void findAll_ShouldReturn_CollectionWithFirstDto(){
    Assertions.assertTrue(firstService.findAll().isEmpty());
    firstService.createAll(getCollectionOfFirstDto());
    Collection<FirstDto> foundFirstDto = firstService.findAll();

    assertNotNull(foundFirstDto);
    assertFalse(foundFirstDto.isEmpty());

  }
  @Test
  void findAll_ShouldReturn_EmptyCollection(){
    Assertions.assertTrue(firstService.findAll().isEmpty());
  }
  //deleteById
  @Test
  void deleteById_ShouldDeleteEntityWithGivenId(){
    FirstDto createdFirstDto = firstService.create(anotherFirstDto);
    assertNotNull(createdFirstDto);
    firstService.deleteById(createdFirstDto.getId());
    System.out.println(createdFirstDto);
    System.out.println(firstService.existById(createdFirstDto.getId()));
    Assertions.assertFalse(firstService.existById(createdFirstDto.getId()));
  }
  //DeleteAll
  @Test
  void deleteAll_ShouldDelete_AllEntity(){
    Collection<FirstDto> createdFirstDto = firstService.createAll(getCollectionOfFirstDto());
    assertNotNull(createdFirstDto);
    assertFalse(createdFirstDto.isEmpty());
    assertTrue(createdFirstDto.size()>1);
    firstService.deleteAll();
    Assertions.assertTrue(firstService.findAll().isEmpty());
  }
  //existById
  @Test
  void existById_ShouldReturnFalse(){
    Assertions.assertFalse(firstService.existById(1L));
  }
  @Test
  void existById_ShouldReturnTrue(){
    FirstDto firstDto = firstService.create(anotherFirstDto);
    assertNotNull(firstDto);
    Assertions.assertTrue(firstService.existById(firstDto.getId()));
  }
}
