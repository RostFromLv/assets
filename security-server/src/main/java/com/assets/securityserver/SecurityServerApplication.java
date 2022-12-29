package com.assets.securityserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@EntityScan(basePackages = "com.assets.commondb.domain")
@SpringBootApplication(scanBasePackages = {"com.assets.exceptionhandler.exception","com.assets.securityserver"})
public class SecurityServerApplication {
  public static void main(String[] args) {
    SpringApplication.run(SecurityServerApplication.class, args);
  }
}
