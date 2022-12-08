package com.assets.firstservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@EntityScan(basePackages = "com.assets.commondb.domain")
@SpringBootApplication(scanBasePackages = {"com.assets.exceptionhandler.exception","com.assets.firstservice"})
public class FirstServiceApplication {
  public static void main(String[] args) {
    SpringApplication.run(FirstServiceApplication.class, args);
  }
}
