package com.assets.securityserver.service;

import com.assets.commondb.domain.User;
import com.assets.commondtos.models.UserDto;
import com.assets.securityserver.mapper.UserMapper;
import com.assets.service.AbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@Transactional
public class UserServiceImpl extends AbstractServiceImpl<User, UserDto,Long, UserMapper,UserRepository> implements UserService{
  private final static String NULL_EMAIL = "Email cannot be null";

  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  public UserServiceImpl(
      BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  @Override
  public UserDto create(UserDto userDto) {
    userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
    return super.create(userDto);
  }

  @Override
  public UserDto findUserByEmail(String email) {
    Assert.notNull(email,NULL_EMAIL);
    return  this.repository.findByEmail(email).map( dbEmail-> this.mapper.toDto(dbEmail)).orElse(null);
  }
}
