package com.assets.securityserver.mapper;

import com.assets.commondb.domain.Role;
import com.assets.commondtos.models.RoleDto;
import com.assets.mapper.AbstractMapper;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
,implementationPackage = "com.assets.securityserver.mapper.impl")
public abstract class RoleMapper extends AbstractMapper<Role, RoleDto> {

  @Override
  public abstract RoleDto toDto( Role role);

  @Override
  public abstract Role toEntity(RoleDto roleDto);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    public abstract void updateProperties(RoleDto rolesDto, @MappingTarget Role role);

}
