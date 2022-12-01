package com.assets.firstservice.service;


import com.assets.commondb.domain.First;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Customized repository.
 * <p>
 * Here are methods customized for using softDelete strategy
 *
 * @author Rosyslav Balushchak
 * @since 1.0.0-SNAPSHOT
 */
@Repository
public interface FirstRepository extends JpaRepository<First, Long> {

  @Override
  @Query(value = "SELECT f FROM First f WHERE f.deleted = false  AND f.id= :id")
  Optional<First> findById(Long id);

  @Override
  @Query("SELECT f FROM First  f WHERE f.deleted = false")
  List<First> findAll();

  @Query("SELECT f FROM First f")
  List<First> findAllFirsts();

  @Override
  @Query("SELECT CASE WHEN COUNT(f)>0  then  true  else false end from First f where  f.deleted = false AND f.id = :id")
  boolean existsById(Long id);
}
