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
  Collection<Second> findAllByFirstId(Long firstId);

  @Override
  @Query("SELECT s FROM Second s where s.deleted = false  and  s.id = :id")
  Optional<Second> findById( Long id);

  @Query(value = "SELECT s FROM Second s WHERE  s.deleted = false ")
  @Override
  List<Second> findAll();

  @Override
  @Query("SELECT CASE WHEN  COUNT (s)>0  then true else  false end from Second s where s.deleted = false AND s.id = :id")
  boolean existsById(@NonNull Long id);
}
