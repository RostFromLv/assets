package com.assets.service;

import java.util.Collection;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * Simple abstract service for future customizing.
 * <p>
 * Here are simple CRUD implementation of AbstractService interface.
 *
 * @param <DTO>        simple transport object for transporting objects to database
 * @param <ID>         type of "id" field
 *
 * @author Rosyslav Balushchak
 * @since 1.0.0-SNAPSHOT
 */
public interface AbstractService<@NonNull DTO, @NonNull ID> {

  DTO create(final DTO dto);

  Collection<DTO> createAll(final Collection<DTO> dtos);

  DTO update(final DTO dto, ID id);

  DTO findById(final ID id);

  Collection<DTO> findAll();

  void deleteById(final ID id);

  void deleteAll();

  boolean existById(final ID id);

}
