package com.assets.service;


import com.example.commondtos.models.FirstDto;
import java.util.Collection;

/**
 * The defining of simple(CRUD) service for implementing it.
 * <p>
 *
 * @author Rosyslav Balushchak
 * @since 1.0.0-SNAPSHOT
 */
public interface CarService extends AbstractService<FirstDto,Long>{

  FirstDto create(final FirstDto dto);

  Collection<FirstDto> createAll(final Collection<FirstDto> dtos);

  FirstDto update(final FirstDto dto, Long id);

  FirstDto findById(final Long id);

  Collection<FirstDto> findAll();

  void deleteById(final Long id);

  void deleteAll();

  boolean existById(final Long id);
}
