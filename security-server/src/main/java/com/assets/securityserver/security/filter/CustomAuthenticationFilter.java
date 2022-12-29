package com.assets.securityserver.security.filter;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private final AuthenticationManager authenticationManager;
  private final int accessTokenLifeTime;
  private final int refreshTokenLifeTime;
  private final Algorithm algorithm;

  public CustomAuthenticationFilter(
      AuthenticationManager authenticationManager, int accessTokenLifeTime,
      int refreshTokenLifeTime, Algorithm algorithm) {
    this.authenticationManager = authenticationManager;
    this.accessTokenLifeTime = accessTokenLifeTime;
    this.refreshTokenLifeTime = refreshTokenLifeTime;
    this.algorithm = algorithm;
  }


  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
                                              HttpServletResponse response)
      throws AuthenticationException {
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(email, password);


    return authenticationManager.authenticate(authenticationToken);
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                          FilterChain chain, Authentication authentication)
      throws IOException, ServletException {
    User user = (User) authentication.getPrincipal();

    String accessToken = JWT.create()
        .withSubject(user.getUsername())
        .withExpiresAt(
            LocalDateTime.now().plusHours(accessTokenLifeTime).atZone(ZoneId.systemDefault())
                .toInstant())
        .withIssuer(request.getRequestURL().toString())
        .withClaim("roles",
            user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(
                Collectors.toList()))
        .sign(algorithm);

    String refreshToken = JWT.create()
        .withSubject(user.getUsername())
        .withExpiresAt(LocalDateTime.now().plusHours(refreshTokenLifeTime).atZone(ZoneId.systemDefault()).toInstant())
        .withIssuer(request.getRequestURL().toString())
        .sign(algorithm);

    Map<String,String> tokens = new HashMap<>();
    tokens.put("access_token",accessToken);
    tokens.put("refresh_token",refreshToken);
    response.setContentType(APPLICATION_JSON_VALUE);
    new ObjectMapper().writeValue(response.getOutputStream(),tokens);
  }

}
