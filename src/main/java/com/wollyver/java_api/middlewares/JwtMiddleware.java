package com.wollyver.java_api.middlewares;

import java.io.IOException;

import com.wollyver.java_api.security.JwtUtil;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtMiddleware implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    // Lógica de inicialização, se necessário
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    HttpServletRequest httpRequest = (HttpServletRequest) request;
    String token = httpRequest.getHeader("Authorization");

    if (token == null || !token.startsWith("Bearer ")) {
      ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED,
          "Token não fornecido ou inválido.");
      return;
    }
    token = token.substring(7);

    boolean isTokenValid = JwtUtil.validateToken(token);

    if (!isTokenValid) {
      ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido.");
      return;
    }

    String username = JwtUtil.getUsernameFromToken(token);
    httpRequest.setAttribute("username", username);

    chain.doFilter(request, response);
  }

  @Override
  public void destroy() {
    // Lógica de limpeza, se necessário
  }
}
