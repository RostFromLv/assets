package com.assets.secondservice.service;

import com.assets.secondservice.domain.Second;
import com.assets.secondservice.mapper.SecondMapper;
import com.assets.service.AbstractServiceImpl;
import com.example.commondtos.models.SecondDto;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class SecondServiceImpl
    extends AbstractServiceImpl<Second, SecondDto,Long, SecondMapper, SecondRepository> implements
    SecondService {

  private final FirstFeignClient firstFeignClient;
  private final SecondRepository secondRepository;
  private final SecondMapper secondMapper;

  private final static String carIdCannotExistMessage = "Car id doesnt exist: %s";

  @Autowired
  public SecondServiceImpl(FirstFeignClient firstFeignClient,
                           SecondRepository secondRepository,
                           SecondMapper secondMapper) {
    this.firstFeignClient = firstFeignClient;
    this.secondRepository = secondRepository;
    this.secondMapper = secondMapper;
  }

  @Override
  @Transactional
  public Collection<SecondDto> getByCarId(Long carId) {
    Assert.notNull(firstFeignClient.foundById(carId),String.format(carIdCannotExistMessage,carId));

    return secondRepository.findAllByCarId(carId).stream().map(secondMapper::toDto).collect(
        Collectors.toSet());
  }
}
