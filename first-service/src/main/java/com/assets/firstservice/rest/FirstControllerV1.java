package com.assets.firstservice.rest;


import com.assets.firstservice.service.FirstService;
import com.example.commondtos.models.FirstDto;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/api/v1/cars")
public class FirstControllerV1 {

  private final FirstService firstService;

  @Autowired
  public FirstControllerV1(FirstService firstService) {
    this.firstService = firstService;
  }

  @PostMapping
  @ResponseStatus(value = HttpStatus.CREATED)
  public FirstDto create(@RequestBody FirstDto workerDto) {
    return firstService.create(workerDto);
  }

  @PostMapping("/create/all")
  @ResponseStatus(value = HttpStatus.CREATED)
  public Collection<FirstDto> createAll(@RequestBody Collection<FirstDto> workerCOllectins) {
    return firstService.createAll(workerCOllectins);
  }

  @PutMapping
  @ResponseStatus(value = HttpStatus.OK)
  public FirstDto update(@RequestBody FirstDto workerDto) {
    Long id = workerDto.getId();
    return firstService.update(workerDto, id);
  }

  @GetMapping("/{id}")
  @ResponseStatus(value = HttpStatus.OK)
  public FirstDto findById(@PathVariable  Long id) {
    return firstService.findById(id);
  }

  @GetMapping
  @ResponseStatus(value = HttpStatus.OK)
  public Collection<FirstDto> findAll() {
    return firstService.findAll();
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void deleteById(@PathVariable Long id) {
    firstService.deleteById(id);
  }

  @DeleteMapping
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void deleteAll() {
    firstService.deleteAll();
  }

}
