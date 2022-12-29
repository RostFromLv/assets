package com.assets.securityserver.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.assets.commondb.domain.RoleName;
import com.assets.commondtos.models.RoleDto;
import com.assets.commondtos.models.UserDto;
import com.assets.securityserver.UserTestData;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
public class UserServiceImplTest extends UserTestData {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final UserService userService;

  @Autowired
  public UserServiceImplTest(UserRepository userRepository,
                             RoleRepository roleRepository,
                             UserService userService) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.userService = userService;
  }

  @BeforeEach
  void beforeEach() {
    userRepository.deleteAll();
    roleRepository.deleteAll();
  }

  //create
  @Test
  void create_shouldCreateUser_andReturn_createdUserDto() {

    UserDto createdUserDto = userService.create(userDto);

    assertNotNull(createdUserDto);
    assertNotNull(createdUserDto.getId());
    Assertions.assertTrue(userRepository.existsById(createdUserDto.getId()));
  }

  //  createAll
  @Test
  void createAll_shouldCreateUsers_andReturn_UserCollection() {

    Assertions.assertTrue(userService.findAll().isEmpty());
    Collection<UserDto> collectionOfCreatedUsersDto =
        userService.createAll(getCollectionOfUsersDto());

    assertFalse(collectionOfCreatedUsersDto.isEmpty());
    Assertions.assertFalse(userService.findAll().isEmpty());
  }

  //update
  @Test
  void update_shouldUpdateUser_AndReturnUpdatedUserDto() {
    UserDto createdUserDto = userService.create(userDto);

    assertNotNull(createdUserDto);

    UserDto updatedDto = userService.update(createdUserDto, createdUserDto.getId());
    assertNotNull(updatedDto);

    assertThat(createdUserDto)
        .usingRecursiveComparison()
        .ignoringFields("id", "createdAt", "updatedAt")
        .isEqualTo(updatedDto);
  }

  //  //findById
  @Test
  void findById_ShouldReturn_existUserDto() {

    UserDto createdUserDto = userService.create(secondUserDto);
    assertNotNull(createdUserDto);

    UserDto foundUserDto = userService.findById(createdUserDto.getId());
    assertNotNull(foundUserDto);

    assertThat(createdUserDto)
        .usingRecursiveComparison()
        .ignoringFields("id", "createdAt", "updatedAt")
        .isEqualTo(foundUserDto);
  }

  @Test
  void findById_ShouldThrow_EntityNotFoundException() {
    assertThrows(EntityNotFoundException.class, () -> userService.findById(1L));
  }

  //findAll
  @Test
  void findAll_ShouldReturn_CollectionWithUserDto() {

    Assertions.assertTrue(userService.findAll().isEmpty());
    userService.createAll(getCollectionOfUsersDto());
    Collection<UserDto> foundUserDto = userService.findAll();

    assertNotNull(foundUserDto);
    assertFalse(foundUserDto.isEmpty());

  }

  @Test
  void findAll_ShouldReturn_EmptyCollection() {
    Assertions.assertTrue(userService.findAll().isEmpty());
  }

  //deleteById
  @Test
  void deleteById_ShouldDeleteEntityWithGivenId() {

    UserDto createdUserDto = userService.create(userDto);

    assertNotNull(createdUserDto);
    userService.deleteById(createdUserDto.getId());
    Assertions.assertFalse(userService.existById(createdUserDto.getId()));
  }

  //DeleteAll
  @Test
  void deleteAll_ShouldDelete_AllEntity() {

    Collection<UserDto> createdUserDto = userService.createAll(getCollectionOfUsersDto());
    assertNotNull(createdUserDto);
    assertFalse(createdUserDto.isEmpty());
    assertTrue(createdUserDto.size() > 1);
    userService.deleteAll();
    Assertions.assertTrue(userService.findAll().isEmpty());
  }

  //existById
  @Test
  void existById_ShouldReturnFalse() {
    Assertions.assertFalse(userService.existById(1L));
  }

  @Test
  void existById_ShouldReturnTrue() {

    UserDto createdUserDto = userService.create(userDto);

    assertNotNull(createdUserDto);
    Assertions.assertTrue(userService.existById(createdUserDto.getId()));
  }

}

