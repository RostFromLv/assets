package com.assets.secondservice.service;


import com.assets.service.AbstractService;
import com.example.commondtos.models.SecondDto;
import java.util.Collection;

public interface SecondService extends AbstractService<SecondDto, Long> {

  SecondDto create(SecondDto secondDto);

  Collection<SecondDto> createAll(Collection<SecondDto> workers);

  SecondDto update(SecondDto secondDto, Long workerId);

  SecondDto findById(Long id);

  Collection<SecondDto> findAll();

  void deleteById(Long id);

  void deleteAll();

  boolean existById(Long id);

  Collection<SecondDto> getByCarId(Long carId);
}