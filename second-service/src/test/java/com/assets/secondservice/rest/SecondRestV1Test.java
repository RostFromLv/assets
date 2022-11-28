package com.assets.secondservice.rest;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.assets.secondservice.AbstractTest;
import com.assets.secondservice.service.SecondRepository;
import com.assets.secondservice.service.SecondService;
import com.example.commondtos.models.SecondDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import java.util.Collection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SuppressWarnings(value = "argument") // For testing cases
public class SecondRestV1Test extends AbstractTest {

  private final SecondRepository secondRepository;
  private final SecondService secondService;

  @LocalServerPort
  private int port;

  @Autowired
  public SecondRestV1Test(SecondRepository secondRepository,
                          SecondService secondService) {
    this.secondRepository = secondRepository;
    this.secondService = secondService;
  }


  @BeforeEach
  void beforeEach() {
    secondRepository.deleteAll();
    RestAssured.reset();

    Assertions.assertTrue(secondService.findAll().isEmpty());
  }

  //create
  @Test
  void createByCorrectDto_ShouldReturn_CreatedDto_and_Status201() {
    SecondDto createdSecondDto = requestJson()
        .body(anotherSecondDto)
        .when()
        .post()
        .then()
        .statusCode(201)
        .extract()
        .as(SecondDto.class);

    org.assertj.core.api.Assertions.assertThat(createdSecondDto).usingRecursiveComparison()
        .ignoringFields("id", "createdAt", "updatedAt")
        .isEqualTo(anotherSecondDto);
  }

  //createAll
  @Test
  void createAll_ShouldReturn_CollectionOfDto_and_Status201() {
    Collection createdWorkers = requestJson()
        .body(getCollectionOfWorkerDto())
        .when()
        .post("/create/all")
        .then()
        .statusCode(201)
        .extract()
        .as(Collection.class);

    System.out.println(createdWorkers);
    assertFalse(createdWorkers.isEmpty());

  }
  //empty collection t create all?

  //findById
  @Test
  void findById_ShouldReturnCarDto_and_Status200() {
    SecondDto createdSecondDto = secondService.create(anotherSecondDto);
    assertNotNull(createdSecondDto);
    SecondDto foundCarDto = request().
        when()
        .get("/" + createdSecondDto.getId())
        .then()
        .statusCode(200)
        .extract()
        .as(SecondDto.class);

    Assertions.assertEquals(createdSecondDto, foundCarDto);
  }

  @Test
  void findByWrongId_ShouldThrow_EntityNotFoundException_and_Status404() {
    request().when()
        .get("/152")
        .then()
        .statusCode(404);
  }

  //find all
  @Test
  void findAll_ShouldReturn_CollectionOfExistDto_and_Status200() {
    secondService.createAll(getCollectionOfWorkerDto());
    Assertions.assertFalse(secondService.findAll().isEmpty());

    Collection foundWorkers = request()
        .when()
        .get()
        .then()
        .statusCode(200)
        .extract()
        .as(Collection.class);

    assertFalse(foundWorkers.isEmpty());

  }

  @Test
  void findAll_ShouldReturn_EmptyList_and_Status200() {
    Collection foundWorkers = request()
        .when()
        .get()
        .then()
        .statusCode(200)
        .extract()
        .as(Collection.class);

    assertTrue(foundWorkers.isEmpty());
  }

  //update
  @Test
  void updateByCorrectDto_shouldReturn_updatedDto_and_Status200() {
    String updatedName = "AnotherBrand";

    SecondDto createdSecondDto = secondService.create(anotherSecondDto);
    assertNotNull(createdSecondDto);

    createdSecondDto.setName(updatedName);

    SecondDto updatedCarDto = requestJson()
        .body(createdSecondDto)
        .when()
        .post()
        .then()
        .statusCode(201)
        .extract()
        .as(SecondDto.class);
    assertNotNull(updatedName);

    Assertions.assertEquals(updatedName, updatedCarDto.getName());
  }

  //delete
  @Test
  void deleteById_ShouldReturn_Status200() {
    SecondDto createdSecondDto = secondService.create(anotherSecondDto);
    assertNotNull(secondService.findById(createdSecondDto.getId()));

    request().when()
        .delete("/" + createdSecondDto.getId())
        .then()
        .statusCode(204);

    Assertions.assertFalse(secondService.existById(createdSecondDto.getId()));
  }

  //delete all
  @Test
  void deleteAll_ShouldReturn_Status200() {
    secondService.createAll(getCollectionOfWorkerDto());
    Assertions.assertFalse(secondService.findAll().isEmpty());

    request().when()
        .delete()
        .then()
        .statusCode(204);

    Assertions.assertTrue(secondService.findAll().isEmpty());
  }

  private RequestSpecification request() {
    return RestAssured.given().port(port).basePath("/api/v1/second");
  }

  private RequestSpecification requestJson() {
    return request().contentType(ContentType.JSON);
  }
}
