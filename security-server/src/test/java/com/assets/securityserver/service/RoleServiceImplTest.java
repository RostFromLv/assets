package com.assets.securityserver.service;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.assets.commondtos.models.RoleDto;
import com.assets.securityserver.RoleTestData;
import java.util.Collection;
import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@ActiveProfiles("test")
@SuppressWarnings(value = "argument")
public class RoleServiceImplTest extends RoleTestData {

  private final RoleRepository roleRepository;
  private final RoleService roleService;

  @Autowired
  public RoleServiceImplTest(RoleRepository roleRepository,
                             RoleService roleService) {
    this.roleRepository = roleRepository;
    this.roleService = roleService;
  }

  @BeforeEach
  void beforeEach(){
    roleRepository.deleteAll();
  }

  //create
  @Test
  void create_shouldCreateUser_andReturn_createdRoleDto(){
    RoleDto createdRoleDto =  roleService.create(roleDto);

    assertNotNull(createdRoleDto);
    assertNotNull(createdRoleDto.getId());
    Assertions.assertTrue(roleRepository.existsById(createdRoleDto.getId()));
  }
  //  createAll
  @Test
  void createAll_shouldCreateUsers_andReturn_UserCollection(){
    Assertions.assertTrue(roleService.findAll().isEmpty());
    Collection<RoleDto> collectionOfCreatedUsersDto = roleService.createAll(getSetOfRoleDto());
    assertFalse(collectionOfCreatedUsersDto.isEmpty());
    Assertions.assertFalse(roleService.findAll().isEmpty());
  }
  //update
  @Test
  void update_shouldUpdateUser_AndReturnUpdatedRoleDto(){
    RoleDto createdRoleDto = roleService.create(roleDto);

    assertNotNull(createdRoleDto);

    RoleDto updatedDto = roleService.update(createdRoleDto, createdRoleDto.getId());
    assertNotNull(updatedDto);

    assertThat(roleDto)
        .usingRecursiveComparison()
        .ignoringFields("id","createdAt","updatedAt")
        .isEqualTo(updatedDto);
  }
  //  //findById
  @Test
  void  findById_ShouldReturn_existRoleDto(){
    RoleDto createdRoleDto = roleService.create(roleDto);
    assertNotNull(createdRoleDto);

    RoleDto foundRoleDto = roleService.findById(roleDto.getId());
    assertNotNull(foundRoleDto);

    assertThat(createdRoleDto)
        .usingRecursiveComparison()
        .ignoringFields("id","createdAt","updatedAt")
        .isEqualTo(foundRoleDto);
  }
  @Test
  void findById_ShouldThrow_EntityNotFoundException(){
    assertThrows(EntityNotFoundException.class,()-> roleService.findById(1L));
  }
  //findAll
  @Test
  void findAll_ShouldReturn_CollectionWithRoleDto(){
    Assertions.assertTrue(roleService.findAll().isEmpty());
    roleService.createAll(getSetOfRoleDto());

    Collection<RoleDto> foundRoleDto = roleService.findAll();

    assertNotNull(foundRoleDto);
    assertFalse(foundRoleDto.isEmpty());

  }
  @Test
  void findAll_ShouldReturn_EmptyCollection(){
    Assertions.assertTrue(roleService.findAll().isEmpty());
  }

  //deleteById
  @Test
  void deleteById_ShouldDeleteEntityWithGivenId(){
    RoleDto createdRoleDto = roleService.create(roleDto);
    assertNotNull(createdRoleDto);

    roleService.deleteById(createdRoleDto.getId());
    Assertions.assertFalse(roleService.existById(createdRoleDto.getId()));
  }
  //DeleteAll
  @Test
  void deleteAll_ShouldDelete_AllEntity(){
    Collection<RoleDto> createdRoleDto = roleService.createAll(getSetOfRoleDto());
    assertNotNull(createdRoleDto);

    assertFalse(createdRoleDto.isEmpty());
    assertTrue(createdRoleDto.size()>1);
    roleService.deleteAll();
    Assertions.assertTrue(roleService.findAll().isEmpty());
  }
  //existById
  @Test
  void existById_ShouldReturnFalse(){
    Assertions.assertFalse(roleService.existById(1L));
  }

  @Test
  void existById_ShouldReturnTrue(){
    RoleDto createdRoleDto = roleService.create(roleDto);
    assertNotNull(createdRoleDto);
    Assertions.assertTrue(roleService.existById(createdRoleDto.getId()));
  }
}
