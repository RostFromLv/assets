package com.assets.securityserver.service;

import com.assets.commondb.domain.Role;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

  @Query("SELECT r from Role r where r.id = :id AND r.deleted = false ")
  Optional<Role> findById(Long id);

  @Query("SELECT r FROM Role r where r.deleted = false")
  List<Role> findAll();

  @Query("SELECT CASE  WHEN COUNT (r)>0 THEN TRUE ELSE FALSE END FROM  Role r WHERE r.deleted = false AND r.id = :id")
  boolean existsById(Long id);

}
