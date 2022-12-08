package com.assets.securityserver.rest;


import com.assets.commondtos.models.UserDto;
import com.assets.securityserver.service.UserService;
import java.util.Collection;
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
@RequestMapping("/api/v1/user")

public class UserRestV1 {

private final   UserService userService;

  public UserRestV1(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDto> findById(@PathVariable Long id){
    return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
  }
  @GetMapping
  public ResponseEntity<Collection<UserDto>> findAll(){
    return new ResponseEntity<>(userService.findAll(),HttpStatus.OK);
  }
  @PostMapping
  public ResponseEntity<UserDto> create(@RequestBody UserDto userDto){
    return new ResponseEntity<>(userService.create(userDto),HttpStatus.CREATED);
  }
  @PostMapping("/create/all")
  public ResponseEntity<Collection<UserDto>> createAll(@RequestBody Collection<UserDto> userDtos){
    return new ResponseEntity<>(userService.createAll(userDtos),HttpStatus.CREATED);
  }
  @PutMapping
  public ResponseEntity<UserDto> update(@RequestBody UserDto userDto){
    return new ResponseEntity<>(userService.update(userDto,userDto.getId()),HttpStatus.OK);
  }
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Long id ){
    userService.deleteById(id);
    return  ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
  @DeleteMapping
  public ResponseEntity<Void> deleteAll(){
    userService.deleteAll();
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
  @GetMapping("/hello")
  public ResponseEntity<String> getHello(){
    return new ResponseEntity<>("Hi,dude",HttpStatus.OK);
  }
}
