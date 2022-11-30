package com.assets.secondservice.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.assets.secondservice.AbstractTest;
import com.assets.commondtos.models.SecondDto;
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
public class SecondServiceIT extends AbstractTest {

  private final SecondRepository secondRepository;
  private final SecondService secondService;

  @Autowired
  public SecondServiceIT(SecondRepository secondRepository, SecondService secondService) {
    this.secondRepository = secondRepository;
    this.secondService = secondService;
  }

  @BeforeEach
  void beforeEach(){
    secondRepository.deleteAll();
  }

  //create
  @Test
  void create_shouldCreateCar_andReturn_createdCarDto(){
     SecondDto createdSecondDto =  secondService.create(anotherSecondDto);

     assertNotNull(createdSecondDto);
     assertNotNull(createdSecondDto.getId());
     Assertions.assertTrue(secondRepository.existsById(createdSecondDto.getId()));
  }
  //createAll
  @Test
  void createAll_shouldCreateCars_andReturn_carCollection(){
    Assertions.assertTrue(secondService.findAll().isEmpty());
    Collection<SecondDto> collectionOfCreatedWorkers = secondService.createAll(getCollectionOfWorkerDto());
    assertFalse(collectionOfCreatedWorkers.isEmpty());
    Assertions.assertFalse(secondService.findAll().isEmpty());
  }
  //update
  @Test
  void update_shouldUpdateCar_AndReturnUpdatedCarDto(){
    SecondDto createdSecondDto = secondService.create(anotherSecondDto);
    assertNotNull(createdSecondDto);
    secondDto.setId(createdSecondDto.getId());

    SecondDto updatedDto = secondService.update(secondDto, secondDto.getId());

    assertNotNull(updatedDto);

    org.assertj.core.api.Assertions.assertThat(secondDto)
        .usingRecursiveComparison()
        .ignoringFields("id","createdAt","updatedAt")
        .isEqualTo(updatedDto);

  }
  //findById
  @Test
  void  findById_ShouldReturn_existCarDto(){
    SecondDto createdSecondDto = secondService.create(anotherSecondDto);
    assertNotNull(createdSecondDto);

    SecondDto foundSecondDto = secondService.findById(createdSecondDto.getId());
    assertNotNull(foundSecondDto);

    org.assertj.core.api.Assertions.assertThat(createdSecondDto)
        .usingRecursiveComparison()
        .ignoringFields("id","createdAt","updatedAt")
        .isEqualTo(foundSecondDto);
  }
  @Test
  void findById_ShouldThrow_EntityNotFoundException(){
    assertThrows(EntityNotFoundException.class,()-> secondService.findById(1L));
  }
  //findAll
  @Test
  void findAll_ShouldReturn_CollectionWithCarDto(){
    Assertions.assertTrue(secondService.findAll().isEmpty());
    secondService.createAll(getCollectionOfWorkerDto());
    Collection<SecondDto> foundSecondDto = secondService.findAll();

    assertNotNull(foundSecondDto);
    assertFalse(foundSecondDto.isEmpty());

  }
  @Test
  void findAll_ShouldReturn_EmptyCollection(){
    Assertions.assertTrue(secondService.findAll().isEmpty());
  }
  //deleteById
  @Test
  void deleteById_ShouldDeleteEntityWithGivenId(){
    SecondDto createdSecondDto = secondService.create(anotherSecondDto);
    assertNotNull(createdSecondDto);
    secondService.deleteById(createdSecondDto.getId());
    System.out.println(createdSecondDto);
    System.out.println(secondService.existById(createdSecondDto.getId()));
    Assertions.assertFalse(secondService.existById(createdSecondDto.getId()));
  }
  //DeleteAll
  @Test
  void deleteAll_ShouldDelete_AllEntity(){
    Collection<SecondDto> createdSecondDto = secondService.createAll(getCollectionOfWorkerDto());
    assertNotNull(createdSecondDto);
    assertFalse(createdSecondDto.isEmpty());
    assertTrue(createdSecondDto.size()>1);
    secondService.deleteAll();
    Assertions.assertTrue(secondService.findAll().isEmpty());
  }
  //existById
  @Test
  void existById_ShouldReturnFalse(){
    Assertions.assertFalse(secondService.existById(1L));
  }
  @Test
  void existById_ShouldReturnTrue(){
    SecondDto secondDto = secondService.create(anotherSecondDto);
    assertNotNull(secondDto);
    Assertions.assertTrue(secondService.existById(secondDto.getId()));
  }
}
