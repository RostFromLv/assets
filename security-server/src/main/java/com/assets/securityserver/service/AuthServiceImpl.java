package com.assets.securityserver.service;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.assets.commondb.domain.Role;
import com.assets.commondb.domain.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

  private final Algorithm algorithm;
  private final UserRepository userRepository;
  private final static String BEARER_PREFIX = "Bearer ";

  @Value("${spring.security.access.token.expiration.time.hours}")
  private int accessTokenLifeTime;

  @Override
  public void getRefreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {

    String authorizationHeader = request.getHeader(AUTHORIZATION);
    if (authorizationHeader!= null && authorizationHeader.startsWith(BEARER_PREFIX)){
      try {
        String refreshToken = authorizationHeader.substring(BEARER_PREFIX.length());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(refreshToken);
        String userEmail = decodedJWT.getSubject();

        User user = userRepository.findByEmail(userEmail).get();

        List<String> roles = user.getRoles().stream().map(users -> users.getRoleName().toString()).collect(
            Collectors.toList());

        String accessToken = JWT.create()
            .withSubject(user.getEmail())
            .withExpiresAt(LocalDateTime.now().plusHours(accessTokenLifeTime).atZone(ZoneId.systemDefault())
                .toInstant())
            .withIssuer(request.getRequestURL().toString())
            .withClaim("roles",roles)
            .sign(algorithm);

        Map<String,String> tokens = new HashMap<>();
        tokens.put("access_token",accessToken);
        tokens.put("refresh_token",refreshToken);
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(),tokens);

      } catch (Exception e) {
        response.setHeader("error", e.getMessage());
        response.setStatus(FORBIDDEN.value());
        Map<String, String> error = new HashMap<>();
        error.put("error_message", e.getMessage());
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), error);
      }
    } else {
      throw new RuntimeException("Refresh token is missing");
    }
  }
}
