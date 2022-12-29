package com.assets.securityserver;

import com.assets.commondb.domain.RoleName;
import com.assets.commondtos.models.RoleDto;
import java.util.Collection;
import java.util.HashSet;

public class RoleTestData {
  protected final  Long id = 1L;
  protected final  Long secondId = 2L;
  protected final  Long thirdId = 3L;
  protected final  RoleName fRoleName = RoleName.ROLE_USER;
  protected final  RoleName sRoleName = RoleName.ROLE_ADMIN;
  protected final  RoleName anotherRoleName = RoleName.ROLE_VIP;

  protected final  RoleDto roleDto = RoleDto.builder()
      .id(id)
      .roleName(fRoleName).build();


  protected final  RoleDto secondRoleDto = RoleDto.builder()
      .id(secondId)
      .roleName(sRoleName).build();;

  protected final  RoleDto anotherRoleDto = RoleDto.builder()
      .id(thirdId)
      .roleName(anotherRoleName).build();

  protected  HashSet<RoleDto> getSetOfRoleDto() {
    HashSet<RoleDto> expected = new HashSet<>();
    expected.add(roleDto);
    expected.add(secondRoleDto);
    return expected;
  }

  protected  HashSet<RoleDto> getSecondSetOfRoleDto() {
    HashSet<RoleDto> expected = new HashSet<>();
    expected.add(roleDto);
    expected.add(anotherRoleDto);
    return expected;
  }
}
