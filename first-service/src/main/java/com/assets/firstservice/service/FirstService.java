package com.assets.firstservice.service;


import com.assets.commondtos.models.FirstDto;
import com.assets.service.AbstractService;
import java.util.Collection;

/**
 * The defining of simple(CRUD) service for implementing it.
 * <p>
 *
 * @author Rosyslav Balushchak
 * @since 1.0.0-SNAPSHOT
 */
public interface FirstService extends AbstractService<FirstDto,Long> {

  FirstDto create(final FirstDto dto);

  Collection<FirstDto> createAll(final Collection<FirstDto> dtos);

  FirstDto update(final FirstDto dto, Long id);

  FirstDto findById(final Long id);

  Collection<FirstDto> findAll();

  void deleteById(final Long id);

  void deleteAll();

  boolean existById(final Long id);
}
