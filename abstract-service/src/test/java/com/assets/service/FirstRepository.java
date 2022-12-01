package com.assets.service;

import com.assets.commondb.domain.First;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FirstRepository extends JpaRepository<First,Long> {
}