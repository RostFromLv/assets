package com.assets.securityserver.service;


import com.assets.commondb.domain.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  @Query("SELECT u from User u where u.id = :id AND u.deleted = false")
  Optional<User> findById(Long id);

  @Query("SELECT  u from User u where u.deleted = false")
  List<User> findAll();

  @Query("SELECT  CASE WHEN COUNT (u)>0 THEN  TRUE ELSE FALSE  END FROM User u where u.deleted = false  AND u.id = :id")
  boolean existsById(Long id);

  @Query("SELECT u FROM User u where  u.email = :email and  u.deleted = false ")
  Optional<User>  findByEmail(String email);
}
