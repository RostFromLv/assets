package com.assets.secondservice.mapper;

import com.assets.mapper.AbstractMapper;
import com.assets.secondservice.domain.Second;
import com.example.commondtos.models.SecondDto;
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
    implementationPackage = "com.assets.secondservice.mapper.impl")
public abstract class SecondMapper extends AbstractMapper<Second, SecondDto> {
  @Override
  public abstract SecondDto toDto(Second second);

  @Override
  public abstract Second toEntity(SecondDto secondDto);

  /**
   * Should ignore updating of  "id"  field to for forbidding of change "id" of entity
   */
  @Override
  @Mapping(target = "id", ignore = true)
  public abstract void updateProperties(SecondDto secondDto, @MappingTarget Second second);
}
