package com.assets.securityserver.security;

import com.assets.commondb.domain.User;
import com.assets.securityserver.service.UserRepository;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class PostgreSQLUserDetailService implements UserDetailsService {

  private final UserRepository userRepository;
  private final static String ENTITY_NOT_FOUND = "Not found entity with email: %s";

  @Autowired
  public PostgreSQLUserDetailService(
      UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {

    User dbUser = userRepository.findByEmail(userEmail).orElseThrow(()-> new EntityNotFoundException(String.format(ENTITY_NOT_FOUND,userEmail))); // Can be exception if user not found
    String userPassword = dbUser.getPassword();
    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
    dbUser.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName().toString())));
    return new org.springframework.security.core.userdetails.User(userEmail, userPassword, authorities);
  }
}
