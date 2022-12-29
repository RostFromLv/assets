package com.assets.securityserver.rest;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.assets.commondb.domain.RoleName;
import com.assets.commondtos.models.RoleDto;
import com.assets.securityserver.RoleTestData;
import com.assets.securityserver.service.RoleRepository;
import com.assets.securityserver.service.RoleService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import java.util.Collection;
import java.util.HashSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class,
    ManagementWebSecurityAutoConfiguration.class})
@SuppressWarnings(value = "argument") // For testing cases
public class RoleControllerV1IT extends RoleTestData {

  private final RoleRepository roleRepository;
  private final RoleService roleService;

  @LocalServerPort
  private int port;

  @Autowired
  public RoleControllerV1IT(RoleRepository roleRepository,
                            RoleService roleService) {
    this.roleRepository = roleRepository;
    this.roleService = roleService;
  }

  @BeforeEach
  void beforeEach() {
    roleRepository.deleteAll();
    RestAssured.reset();

    Assertions.assertTrue(roleService.findAll().isEmpty());
  }

  //create
  @Test
  void createByCorrectDto_ShouldReturn_CreatedDto_and_Status201() {
    RoleDto createdRoleDto = requestJson()
        .body(anotherRoleDto)
        .when()
        .post()
        .then()
        .statusCode(201)
        .extract()
        .as(RoleDto.class);

    assertThat(createdRoleDto).usingRecursiveComparison()
        .ignoringFields("id", "createdAt", "updatedAt")
        .isEqualTo(anotherRoleDto);
  }

  //createAll
  @Test
  void createAll_ShouldReturn_CollectionOfRoleDto_and_Status201() {
    HashSet createdRole = requestJson()
        .body(getSetOfRoleDto())
        .when()
        .post("/all")
        .then()
        .statusCode(201)
        .extract()
        .as(HashSet.class);

    assertFalse(createdRole.isEmpty());

  }

  //findById
  @Test
  void findById_ShouldReturnRoleDto_and_Status200() {
    RoleDto createdRoleDto = roleService.create(anotherRoleDto);
    assertNotNull(createdRoleDto);
    RoleDto foundRoleDto = request().
        when()
        .get("/" + createdRoleDto.getId())
        .then()
        .statusCode(200)
        .extract()
        .as(RoleDto.class);

    Assertions.assertEquals(createdRoleDto, foundRoleDto);
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
    roleService.createAll(getSetOfRoleDto());
    Assertions.assertFalse(roleService.findAll().isEmpty());

    HashSet foundRole = request()
        .when()
        .get()
        .then()
        .statusCode(200)
        .extract()
        .as(HashSet.class);

    assertFalse(foundRole.isEmpty());

  }

  @Test
  void findAll_ShouldReturn_EmptyList_and_Status200() {
    HashSet foundRole = request()
        .when()
        .get()
        .then()
        .statusCode(200)
        .extract()
        .as(HashSet.class);

    assertTrue(foundRole.isEmpty());
  }

  //update
  @Test
  void updateByCorrectDto_shouldReturn_updatedDto_and_Status200() {
    RoleName updatedRoleName = RoleName.ROLE_USER;

    RoleDto createdRoleDto = roleService.create(anotherRoleDto);
    assertNotNull(createdRoleDto);

    createdRoleDto.setRoleName(updatedRoleName);

    RoleDto updatedRoleDto = requestJson()
        .body(createdRoleDto)
        .when()
        .post()
        .then()
        .statusCode(201)
        .extract()
        .as(RoleDto.class);

    assertNotNull(updatedRoleDto);

    Assertions.assertEquals(updatedRoleName, updatedRoleDto.getRoleName());
  }

  //delete
  @Test
  void deleteById_ShouldReturn_Status200() {
    RoleDto createdRoleDto = roleService.create(anotherRoleDto);
    assertNotNull(roleService.findById(createdRoleDto.getId()));

    request().when()
        .delete("/" + createdRoleDto.getId())
        .then()
        .statusCode(204);

    Assertions.assertFalse(roleService.existById(createdRoleDto.getId()));
  }

  //delete all
  @Test
  void deleteAll_ShouldReturn_Status200() {
    roleService.createAll(getSetOfRoleDto());
    Assertions.assertFalse(roleService.findAll().isEmpty());

    request().when()
        .delete()
        .then()
        .statusCode(204);

    Assertions.assertTrue(roleService.findAll().isEmpty());
  }

  private RequestSpecification request() {
    return RestAssured.given().port(port).basePath("/api/v1/role");
  }

  private RequestSpecification requestJson() {
    return request().contentType(ContentType.JSON);
  }
}
