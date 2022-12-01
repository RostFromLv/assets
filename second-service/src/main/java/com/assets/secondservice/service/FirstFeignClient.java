package com.assets.secondservice.service;

import com.assets.commondtos.models.FirstDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "first-service")
public interface FirstFeignClient {
  String carUri  = "/api/v1/first";

  @GetMapping(carUri+"/{id}")
  FirstDto foundById(@PathVariable final Long id);

}
