package com.assets.firstservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication(scanBasePackages = {"com.assets.exceptionhandler.exception","com.assets.firstservice"})
public class FirstServiceApplication {
  public static void main(String[] args) {
    SpringApplication.run(FirstServiceApplication.class, args);
  }
}
