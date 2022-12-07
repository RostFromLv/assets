package com.assets.securityserver.mapper;


import com.assets.commondb.domain.User;
import com.assets.commondtos.models.UserDto;
import com.assets.mapper.AbstractMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    implementationPackage = "com.assets.securityserver.mapper.impl")
public abstract class UserMapper extends AbstractMapper<User, UserDto> {

  @Override
  public abstract UserDto toDto(User user);

  @Override
  public abstract User toEntity(UserDto userDto);

  @Override
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  public abstract void updateProperties(UserDto userDto, @MappingTarget User user);
}
