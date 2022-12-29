package com.assets.securityserver.service;


import com.assets.commondb.domain.Role;
import com.assets.commondtos.models.RoleDto;
import com.assets.securityserver.mapper.RoleMapper;
import com.assets.service.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleServiceImpl
    extends AbstractServiceImpl<Role, RoleDto, Long, RoleMapper, RoleRepository>
    implements RoleService {

}
