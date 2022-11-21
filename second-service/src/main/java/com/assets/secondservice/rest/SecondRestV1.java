package com.assets.secondservice.rest;

import com.assets.secondservice.service.SecondService;
import com.example.commondtos.models.SecondDto;
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
@RequestMapping("/api/v1/workers")
public class SecondRestV1 {
  private final SecondService secondService;

  @Autowired
  public SecondRestV1(SecondService secondService) {
    this.secondService = secondService;
  }

  @PostMapping
  @ResponseStatus(value = HttpStatus.CREATED)
  public SecondDto create(@RequestBody SecondDto secondDto) {
    return secondService.create(secondDto);
  }

  @PostMapping("/create/all")
  @ResponseStatus(value = HttpStatus.CREATED)
  public Collection<SecondDto> createAll(@RequestBody Collection<SecondDto> workerCOllectins) {
    return secondService.createAll(workerCOllectins);
  }

  @PutMapping
  @ResponseStatus(value = HttpStatus.OK)
  public SecondDto update(@RequestBody SecondDto secondDto) {
    Long id = secondDto.getId();
    return secondService.update(secondDto, id);
  }

  @GetMapping("/{id}")
  @ResponseStatus(value = HttpStatus.OK)
  public SecondDto findById(@PathVariable Long id) {
    return secondService.findById(id);
  }

  @GetMapping
  @ResponseStatus(value = HttpStatus.OK)
  public Collection<SecondDto> findAll() {
    return secondService.findAll();
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void deleteById(@PathVariable Long id) {
    secondService.deleteById(id);
  }

  @DeleteMapping
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void deleteAll() {
    secondService.deleteAll();
  }

  @GetMapping("/car/{carId}")
  @ResponseStatus(value = HttpStatus.OK)
  public  Collection<SecondDto> getByCarId(@PathVariable Long carId) {
    return secondService.getByCarId(carId);
  }
}
