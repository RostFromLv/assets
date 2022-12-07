package com.assets.securityserver.service;

import com.assets.commondb.domain.User;
import com.assets.commondtos.models.UserDto;
import com.assets.securityserver.mapper.UserMapper;
import com.assets.service.AbstractServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends AbstractServiceImpl<User, UserDto,Long, UserMapper,UserRepository> implements UserService{
}
