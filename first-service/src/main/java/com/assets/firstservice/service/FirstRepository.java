package com.assets.firstservice.service;


import com.assets.firstservice.domain.First;
import java.util.List;
import java.util.Optional;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Customized repository.
 *
 * Here are methods customized for using softDelete strategy
 * @since 1.0.0-SNAPSHOT
 * @author Rosyslav Balushchak
 */
@Repository
public interface FirstRepository extends JpaRepository<First, Long> {

  @NonNull
  @Override
  @Transactional(readOnly = true)
  @Query(nativeQuery = true,
      value = "select * from first where first.deleted = false  and first.id = :id ")
  Optional<First> findById(@NonNull Long id);

  @NonNull
  @Override
  @Query(nativeQuery = true,
      value = "select  * from first where first.deleted = false")
  @Transactional(readOnly = true)
  List<First> findAll();

  @NonNull
  @Query(nativeQuery = true,
      value = "select * from  first")
  @Transactional(readOnly = true)
  List<First> findAllCars();

  @Override
  @Transactional(readOnly = true)
  @Query(nativeQuery = true,
      value = "select case when exists(select  * from first where first.id = :id and first.deleted = false) " +
          "THEN CAST(1 as BIT) ELSE CAST(0 AS BIT) END")
  boolean existsById(@NonNull Long id);
}
