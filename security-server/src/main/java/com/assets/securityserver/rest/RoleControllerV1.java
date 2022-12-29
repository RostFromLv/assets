package com.assets.securityserver.rest;

import com.assets.commondtos.models.RoleDto;
import com.assets.securityserver.service.RoleService;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/role")
public class RoleControllerV1 {

  private final RoleService roleService;

  @Autowired
  public RoleControllerV1(RoleService roleService) {
    this.roleService = roleService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<RoleDto> findById(@PathVariable Long id){
    return new ResponseEntity<>(roleService.findById(id), HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<Collection<RoleDto>> findAll(){
    return new ResponseEntity<>(roleService.findAll(),HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<RoleDto> create(@RequestBody  RoleDto roleDto){
    return new ResponseEntity<>(roleService.create(roleDto),HttpStatus.CREATED);
  }
  @PostMapping("/all")
  public ResponseEntity<Collection<RoleDto>> createAll(@RequestBody Collection<RoleDto> roleDtos){
    return new ResponseEntity<>(roleService.createAll(roleDtos),HttpStatus.CREATED);
  }

  @PutMapping
  public ResponseEntity<RoleDto> update(@RequestBody RoleDto roleDto){
    return new ResponseEntity<>(roleService.update(roleDto,roleDto.getId()),HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Long id){
    roleService.deleteById(id);
    return  ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @DeleteMapping
  public ResponseEntity<Void> deleteAll(){
      roleService.deleteAll();
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }



}
