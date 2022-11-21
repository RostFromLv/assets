package com.assets.secondservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@EnableFeignClients
@SpringBootApplication(scanBasePackages = {"com.assets.exceptionhandler.exception","com.assets.secondservice"})
public class SecondServiceApplication {
  public static void main(String[] args) {
    SpringApplication.run(SecondServiceApplication.class, args);
  }

}
