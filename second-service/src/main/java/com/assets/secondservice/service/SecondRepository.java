package com.assets.secondservice.service;

import com.assets.secondservice.domain.Second;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SecondRepository extends JpaRepository<Second,Long> {
  Collection<Second> findAllByCarId(Long carId);

  @Override
  @NonNull
  @Query(nativeQuery = true,
      value = "select * from second where second .deleted = false  and second.id = :id")
  Optional<Second> findById(@NonNull Long id);

  @NonNull
  @Query(nativeQuery = true,
  value = "select  * from  second where second.deleted = false")
  @Override
  List<Second> findAll();

  @Override
  @Query(nativeQuery = true,
  value = "select case when exists(select * from second where second.id = :id and second.deleted = false)" +
      "then CAST(1 as BIT) ELSE CAST(0 as BIT) END")
  boolean existsById(@NonNull Long id);
}
