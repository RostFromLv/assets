package com.assets.service;


import static org.springframework.util.Assert.notNull;

import com.assets.mapper.AbstractMapper;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * Simple abstract service for future customizing.
 * <p>
 * Here are simple CRUD implementation of AbstractService interface.
 *
 * @param <Entity>     simple entity for working with database
 * @param <DTO>        simple transport object for transporting objects to database
 * @param <ID>         type of "id" field
 * @param <Mapper>     customized mapper for mapping entity to dto and vice versa.
 * @param <Repository> customized repository
 *
 * @author Rosyslav Balushchak
 * @since 1.0.0-SNAPSHOT
 */
public abstract class AbstractServiceImpl<Entity, DTO, ID, Mapper extends AbstractMapper<Entity, DTO>, Repository extends JpaRepository<Entity, ID>>
    implements AbstractService<DTO, ID> {

  @SuppressWarnings("initialization.field.uninitialized")
  protected Mapper mapper;

  @SuppressWarnings("initialization.field.uninitialized")
  protected Repository repository;

  private static final String CREATED_ENTITY_NULL_MESSAGE = "Created entity is null";
  private static final String ENTITY_NOT_FOUND_EXCEPTION_MESSAGE = "Entity not found with id %s";
  private static final String PASSED_NULL_DTO = "Passed dto cannot be null";

  @Autowired
  public void setMapper(Mapper mapper) {
    this.mapper = mapper;
  }

  @Autowired
  public void setRepository(Repository repository) {
    this.repository = repository;
  }

  @Override
  @Transactional
  public DTO create(final DTO dto) {

    Entity entity = repository.save(mapper.toEntity(dto));

    notNull(entity, CREATED_ENTITY_NULL_MESSAGE);

    return mapper.toDto(entity);
  }

  @Override
  @Transactional
  public Collection<DTO> createAll(Collection<DTO> dtos) {
    Collection<DTO> createdDtos = new HashSet<>();
    for (DTO dto : dtos) {
      createdDtos.add(create(dto));
    }
    return createdDtos;
  }

  @Override
  @Transactional
  public DTO update(final DTO dto, final ID id) {
    notNull(dto,PASSED_NULL_DTO);
    notNull(id, "Update id cannot be null");

    Entity entity = repository.findById(id)
        .orElseThrow(() -> new  EntityNotFoundException(String.format(ENTITY_NOT_FOUND_EXCEPTION_MESSAGE, id)));

    mapper.updateProperties(dto, entity);

    Entity savedEntity = repository.save(entity);

    return mapper.toDto(savedEntity);
  }

  @Override
  @Transactional(readOnly = true)
  public DTO findById(final ID id) {
    Assert.notNull(id, "Id cannot be null");
    return repository.findById(id).map(mapper::toDto)
        .orElseThrow(() -> new EntityNotFoundException(String.format(ENTITY_NOT_FOUND_EXCEPTION_MESSAGE, id)));
  }

  @Override
  @Transactional(readOnly = true)
  public Collection<DTO> findAll() {
    return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toSet());
  }

  @Override
  @Transactional
  public void deleteById(final ID id) {
    repository.deleteById(id);
  }

  @Override
  @Transactional
  public void deleteAll() {
    repository.deleteAll();
  }

  @Override
  @Transactional(readOnly = true)
  public boolean existById(final ID id) {
    return repository.existsById(id);
  }
}
