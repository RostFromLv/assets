package com.assets.firstservice.rest;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.assets.firstservice.service.FirstRepository;
import com.assets.firstservice.service.FirstService;
import com.example.commondtos.models.FirstDto;
import com.assets.firstservice.AbstractTest;
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
public class FirstRestV1ITest extends AbstractTest {

  private final FirstRepository firstRepository;
  private final FirstService firstService;

  @LocalServerPort
  private int port;

  @Autowired
  public FirstRestV1ITest(FirstRepository firstRepository,
                          FirstService firstService) {
    this.firstRepository = firstRepository;
    this.firstService = firstService;
  }

  @BeforeEach
  void beforeEach() {
    firstRepository.deleteAll();
    RestAssured.reset();

    Assertions.assertTrue(firstService.findAll().isEmpty());
  }

  //create
  @Test
  void createByCorrectDto_ShouldReturn_CreatedDto_and_Status201() {
    FirstDto createdFirstDto = requestJson()
        .body(anotherFirstDto)
        .when()
        .post()
        .then()
        .statusCode(201)
        .extract()
        .as(FirstDto.class);

    org.assertj.core.api.Assertions.assertThat(createdFirstDto).usingRecursiveComparison()
        .ignoringFields("id", "createdAt", "updatedAt")
        .isEqualTo(anotherFirstDto);
  }

  //createAll
  @Test
  void createAll_ShouldReturn_CollectionOfDto_and_Status201() {
    Collection createdCars = requestJson()
        .body(getCollectionOfCarDto())
        .when()
        .post("/create/all")
        .then()
        .statusCode(201)
        .extract()
        .as(Collection.class);

    System.out.println(createdCars);
    assertFalse(createdCars.isEmpty());

  }
  //empty collection t create all?

  //findById
  @Test
  void findById_ShouldReturnCarDto_and_Status200() {
    FirstDto createdFirstDto = firstService.create(anotherFirstDto);
    assertNotNull(createdFirstDto);
    FirstDto foundFirstDto = request().
        when()
        .get("/" + createdFirstDto.getId())
        .then()
        .statusCode(200)
        .extract()
        .as(FirstDto.class);

    Assertions.assertEquals(createdFirstDto, foundFirstDto);
  }

  @Test
  void findByWrongId_ShouldThrow_EntityNotFoundException_and_Status404() {
    request().when()
        .get("/1000")
        .then()
        .statusCode(404);
  }

  //find all
  @Test
  void findAll_ShouldReturn_CollectionOfExistDto_and_Status200() {
    firstService.createAll(getCollectionOfCarDto());
    Assertions.assertFalse(firstService.findAll().isEmpty());

    Collection foundCars = request()
        .when()
        .get()
        .then()
        .statusCode(200)
        .extract()
        .as(Collection.class);

    assertFalse(foundCars.isEmpty());

  }

  @Test
  void findAll_ShouldReturn_EmptyList_and_Status200() {
    Collection foundCars = request()
        .when()
        .get()
        .then()
        .statusCode(200)
        .extract()
        .as(Collection.class);

    assertTrue(foundCars.isEmpty());
  }

  //update
  @Test
  void updateByCorrectDto_shouldReturn_updatedDto_and_Status200() {
    String updatedBrand = "AnotherBrand";

    FirstDto createdFirstDto = firstService.create(anotherFirstDto);
    assertNotNull(createdFirstDto);

    createdFirstDto.setBrand(updatedBrand);

    FirstDto updatedFirstDto = requestJson()
        .body(createdFirstDto)
        .when()
        .post()
        .then()
        .statusCode(201)
        .extract()
        .as(FirstDto.class);
    assertNotNull(updatedBrand);

    Assertions.assertEquals(updatedBrand, updatedFirstDto.getBrand());
  }

  //delete
  @Test
  void deleteById_ShouldReturn_Status200() {
    FirstDto createdFirstDto = firstService.create(anotherFirstDto);
    assertNotNull(firstService.findById(createdFirstDto.getId()));

    request().when()
        .delete("/" + createdFirstDto.getId())
        .then()
        .statusCode(204);

    Assertions.assertFalse(firstService.existById(createdFirstDto.getId()));
  }

  //delete all
  @Test
  void deleteAll_ShouldReturn_Status200() {
    firstService.createAll(getCollectionOfCarDto());
    Assertions.assertFalse(firstService.findAll().isEmpty());

    request().when()
        .delete()
        .then()
        .statusCode(204);

    Assertions.assertTrue(firstService.findAll().isEmpty());
  }

  private RequestSpecification request() {
    return RestAssured.given().port(port).basePath("/api/v1/first");
  }

  private RequestSpecification requestJson() {
    return request().contentType(ContentType.JSON);
  }
}
