package com.assets.mapper;

import com.assets.domain.Car;
import com.assets.commondtos.models.FirstDto;
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
    implementationPackage = "com.assets.abstractservice.mapper.impl")
public abstract class CarMapper extends AbstractMapper<Car, FirstDto> {

  @Override
  public abstract FirstDto toDto(Car car);

  @Override
  public abstract Car toEntity(FirstDto firstDto);

  /**
   * Should ignore updating of  "id"  field to for forbidding of change "id" of entity
   */
  @Override
  @Mapping(target = "id", ignore = true)
  public abstract void updateProperties(FirstDto firstDto, @MappingTarget Car car);
}
