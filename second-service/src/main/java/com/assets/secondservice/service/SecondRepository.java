package com.assets.secondservice.service;

import com.assets.commondb.domain.Second;
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
//  @Query(nativeQuery = true,
//      value = "select * from second_s where second_s .deleted = false  and second_s.id = :id")
  @Query(value = "SELECT s FROM second_s s where s.deleted = false  and  s.id = ?1")
  Optional<Second> findById(@NonNull Long id);

  @NonNull
//  @Query(nativeQuery = true,
//  value = "select  * from  second_s where second_s.deleted = false")
  @Query(value = "SELECT s FROM second_s s WHERE  s.deleted = false ")
  @Override
  List<Second> findAll();

  @Override
//  @Query(nativeQuery = true,
//  value = "select case when exists(select * from second_s where second_s.id = :id and second_s.deleted = false)" +
//      "then CAST(1 as BIT) ELSE CAST(0 as BIT) END")
  @Query(value = "SELECT CASE WHEN  COUNT (s)>0 AND s.deleted = false AND s.id = ?1 then true else  false end from second_s s")
  boolean existsById(@NonNull Long id);
}
