package com.assets.secondservice.rest;

import com.assets.secondservice.service.SecondService;
import com.assets.commondtos.models.SecondDto;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/second")
public class SecondRestV1 {
  private final SecondService secondService;

  @Autowired
  public SecondRestV1(SecondService secondService) {
    this.secondService = secondService;
  }

  @PostMapping
  public ResponseEntity<SecondDto> create(@RequestBody SecondDto secondDto) {
    return new ResponseEntity<>(secondService.create(secondDto),HttpStatus.CREATED);
  }

  @PostMapping("/create/all")
  public ResponseEntity<Collection<SecondDto>> createAll(@RequestBody Collection<SecondDto> workerCOllectins) {
    return new ResponseEntity<>(secondService.createAll(workerCOllectins),HttpStatus.CREATED);
  }

  @PutMapping
  public ResponseEntity<SecondDto> update(@RequestBody SecondDto secondDto) {
    Long id = secondDto.getId();
    return new ResponseEntity<>(secondService.update(secondDto, id),HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<SecondDto> findById(@PathVariable Long id) {
    return new ResponseEntity<>(secondService.findById(id),HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<Collection<SecondDto>> findAll() {
    return new ResponseEntity<>(secondService.findAll(),HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Long id) {
    secondService.deleteById(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @DeleteMapping
  public ResponseEntity<Void> deleteAll() {
    secondService.deleteAll();
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @GetMapping("/first/{firstId}")
  public  ResponseEntity<Collection<SecondDto>> getByFirstId(@PathVariable Long firstId) {
    return new ResponseEntity<>(secondService.getByFirstId(firstId),HttpStatus.OK);
  }
}
