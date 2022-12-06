package com.assets.firstservice.rest;



import com.assets.commondtos.models.FirstDto;
import com.assets.firstservice.service.FirstService;
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
@RequestMapping("/api/v1/first")
public class FirstControllerV1 {

  private final FirstService firstService;

  @Autowired
  public FirstControllerV1(FirstService firstService) {
    this.firstService = firstService;
  }

  @PostMapping
  public ResponseEntity<FirstDto> create(@RequestBody FirstDto workerDto) {
    return new ResponseEntity<>(firstService.create(workerDto),HttpStatus.CREATED);
  }

  @PostMapping("/create/all")
  public ResponseEntity<Collection<FirstDto>> createAll(@RequestBody Collection<FirstDto> workerCOllectins) {
    return new ResponseEntity<>(firstService.createAll(workerCOllectins),HttpStatus.CREATED);
  }

  @PutMapping
  public ResponseEntity<FirstDto> update(@RequestBody FirstDto workerDto) {
    Long id = workerDto.getId();
    return new ResponseEntity<>(firstService.update(workerDto, id),HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<FirstDto> findById(@PathVariable  Long id) {
    return new ResponseEntity<>(firstService.findById(id),HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<Collection<FirstDto>> findAll() {
    return new ResponseEntity<>(firstService.findAll(),HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Long id) {
    firstService.deleteById(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @DeleteMapping
  public ResponseEntity<Void> deleteAll() {
    firstService.deleteAll();
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

}
