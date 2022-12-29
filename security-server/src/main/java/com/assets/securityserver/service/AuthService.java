package com.assets.securityserver.service;


import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AuthService {
  void getRefreshToken(HttpServletRequest request, HttpServletResponse response)
      throws IOException;
}
