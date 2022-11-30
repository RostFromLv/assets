package com.assets.firstservice.service;


import com.assets.commondb.domain.First;
import java.util.List;
import java.util.Optional;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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
  @Query(value = "SELECT f FROM first_s f WHERE f.deleted = false  AND f.id= ?1")
  Optional<First> findById(@NonNull Long id);

  @NonNull
  @Override
  @Query(value = "SELECT f FROM first_s  f WHERE f.deleted = false")
  List<First> findAll();

  @NonNull
  @Query(value = "SELECT f FROM first_s f")
  List<First> findAllCars();

  @Override
  @Query(value = "SELECT CASE WHEN COUNT(f)>0 AND f.deleted = false AND f.id =?1 then  true  else false end from first_s f ")

  boolean existsById(@NonNull Long id);

}
