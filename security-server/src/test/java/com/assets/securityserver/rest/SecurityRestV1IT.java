package com.assets.securityserver.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.assets.commondtos.models.UserDto;
import com.assets.securityserver.AbstractTest;
import com.assets.securityserver.service.UserRepository;
import com.assets.securityserver.service.UserService;
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
public class SecurityRestV1IT extends AbstractTest {

  private final UserRepository userRepository;
  private final UserService userService;

  @LocalServerPort
  private int port;

  @Autowired
  public SecurityRestV1IT(UserRepository userRepository,
                          UserService userService) {
    this.userRepository = userRepository;
    this.userService = userService;
  }

  @BeforeEach
  void beforeEach() {
    userRepository.deleteAll();
    RestAssured.reset();

    Assertions.assertTrue(userService.findAll().isEmpty());
  }

  //create
  @Test
  void createByCorrectDto_ShouldReturn_CreatedDto_and_Status201() {
    UserDto createdUserDto = requestJson()
        .body(anotherUserDto)
        .when()
        .post()
        .then()
        .statusCode(201)
        .extract()
        .as(UserDto.class);

   assertThat(createdUserDto).usingRecursiveComparison()
        .ignoringFields("id", "createdAt", "updatedAt")
        .isEqualTo(anotherUserDto);
  }

  //createAll
  @Test
  void createAll_ShouldReturn_CollectionOfDto_and_Status201() {
    Collection createdUser = requestJson()
        .body(getCollectionOfUsersDto())
        .when()
        .post("/create/all")
        .then()
        .statusCode(201)
        .extract()
        .as(Collection.class);

    assertFalse(createdUser.isEmpty());

  }

  //findById
  @Test
  void findById_ShouldReturnUserDto_and_Status200() {
    UserDto createdUserDto = userService.create(anotherUserDto);
    assertNotNull(createdUserDto);
    UserDto foundUserDto = request().
        when()
        .get("/" + createdUserDto.getId())
        .then()
        .statusCode(200)
        .extract()
        .as(UserDto.class);

    Assertions.assertEquals(createdUserDto, foundUserDto);
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
    userService.createAll(getCollectionOfUsersDto());
    Assertions.assertFalse(userService.findAll().isEmpty());

    Collection foundUser = request()
        .when()
        .get()
        .then()
        .statusCode(200)
        .extract()
        .as(Collection.class);

    assertFalse(foundUser.isEmpty());

  }

  @Test
  void findAll_ShouldReturn_EmptyList_and_Status200() {
    Collection foundUser = request()
        .when()
        .get()
        .then()
        .statusCode(200)
        .extract()
        .as(Collection.class);

    assertTrue(foundUser.isEmpty());
  }

  //update
  @Test
  void updateByCorrectDto_shouldReturn_updatedDto_and_Status200() {
    String updatedName = "AnotherName";

    UserDto createdUserDto = userService.create(anotherUserDto);
    assertNotNull(createdUserDto);

    createdUserDto.setName(updatedName);

    UserDto updatedUserDto = requestJson()
        .body(createdUserDto)
        .when()
        .post()
        .then()
        .statusCode(201)
        .extract()
        .as(UserDto.class);

    assertNotNull(updatedUserDto);

    Assertions.assertEquals(updatedName, updatedUserDto.getName());
  }

  //delete
  @Test
  void deleteById_ShouldReturn_Status200() {
    UserDto createdUserDto = userService.create(anotherUserDto);
    assertNotNull(userService.findById(createdUserDto.getId()));

    request().when()
        .delete("/" + createdUserDto.getId())
        .then()
        .statusCode(204);

    Assertions.assertFalse(userService.existById(createdUserDto.getId()));
  }

  //delete all
  @Test
  void deleteAll_ShouldReturn_Status200() {
    userService.createAll(getCollectionOfUsersDto());
    Assertions.assertFalse(userService.findAll().isEmpty());

    request().when()
        .delete()
        .then()
        .statusCode(204);

    Assertions.assertTrue(userService.findAll().isEmpty());
  }

  private RequestSpecification request() {
    return RestAssured.given().port(port).basePath("/api/v1/user");
  }

  private RequestSpecification requestJson() {
    return request().contentType(ContentType.JSON);
  }
}
