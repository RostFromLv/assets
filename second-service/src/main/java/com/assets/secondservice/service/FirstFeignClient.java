package com.assets.secondservice.service;

import com.example.commondtos.models.FirstDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "first-service")
public interface FirstFeignClient {
  String carUri  = "/api/v1/cars";

  @GetMapping(carUri+"/{id}")
  FirstDto foundById(@PathVariable final Long id);

}
