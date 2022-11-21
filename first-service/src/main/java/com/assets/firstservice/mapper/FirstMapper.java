package com.assets.firstservice.mapper;

import com.assets.mapper.AbstractMapper;
import com.example.commondtos.models.FirstDto;
import com.assets.firstservice.domain.First;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * Customized mapper
 * <p>
 *
 * @author Rosyslav Balushchak
 * @see AbstractMapper
 * @since 1.0.0-SNAPSHOT
 */
@Mapper(componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    implementationPackage = "com.assets.firstservice.mapper.impl")
public abstract class FirstMapper extends AbstractMapper<First, FirstDto> {

  @Override
  public abstract FirstDto toDto(First first);

  @Override
  public abstract First toEntity(FirstDto firstDto);

  /**
   * Should ignore updating of  "id"  field to for forbidding of change "id" of entity
   */
  @Override
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  public abstract void updateProperties(FirstDto firstDto, @MappingTarget First first);
}
