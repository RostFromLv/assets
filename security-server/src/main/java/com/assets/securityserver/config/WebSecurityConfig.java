package com.assets.securityserver.config;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import com.assets.securityserver.security.filter.CustomAuthenticationFilter;
import com.assets.securityserver.security.filter.CustomAuthorizationFilter;
import com.assets.securityserver.security.PostgreSQLUserDetailService;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private final PostgreSQLUserDetailService userDetailService;

  @Value("${spring.security.secret}")
  private String secret;
  @Value("${spring.security.access.token.expiration.time.hours}")
  private int accessTokenLifeTime;
  @Value("${spring.security.refresh.token.expiration.time.hours}")
  private int refreshTokenLifeTime;
  @Value("${spring.security.encoder-strength}")
  private int encoderStrength;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
  }


  @Override
  protected void configure(HttpSecurity http) throws Exception {
    CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean(),
        accessTokenLifeTime, refreshTokenLifeTime, getAlgorithm());
    customAuthenticationFilter.setFilterProcessesUrl("/api/v1/login");
    http.csrf().disable();
    http.sessionManagement().sessionCreationPolicy(STATELESS);
    http.authorizeRequests().antMatchers("/api/v1/login","/api/v1/auth/token/refresh").permitAll();
    http.authorizeRequests().antMatchers(GET,"/api/v1/user/**").hasAnyAuthority("ROLE_USER");
    http.authorizeRequests().antMatchers(PUT,"/api/v1/user/**").hasAnyAuthority("ROLE_VIP","ROLE_ADMIN");
    http.authorizeRequests().antMatchers(POST,"/api/v1/user/**").hasAnyAuthority("ADMIN");
    http.authorizeRequests().anyRequest().authenticated();

    http.addFilter(customAuthenticationFilter);
    http.addFilterBefore(new CustomAuthorizationFilter(getAlgorithm()), UsernamePasswordAuthenticationFilter.class);
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(encoderStrength);
  }

  @Bean
  public Algorithm getAlgorithm(){
    return Algorithm.HMAC256(secret.getBytes());
  }

}
