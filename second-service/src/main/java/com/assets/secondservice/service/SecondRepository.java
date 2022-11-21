package com.assets.secondservice.service;

import com.assets.secondservice.domain.Second;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecondRepository extends JpaRepository<Second,Long> {
  Collection<Second> findAllByCarId(Long carId);
}
