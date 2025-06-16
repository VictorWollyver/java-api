package com.wollyver.java_api.services;

import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wollyver.java_api.exceptions.Unauthorized;
import com.wollyver.java_api.models.User;
import com.wollyver.java_api.security.JwtUtil;

@Service
public class AuthService {

  private final UserService userService;
  private final PasswordEncoder passwordEncoder;

  public AuthService(UserService userService, PasswordEncoder passwordEncoder) {
    this.userService = userService;
    this.passwordEncoder = passwordEncoder;
  }

  public String auth(String name, String password) {
    User user = userService.getUserByName(name);

    boolean isValidPassword = this.passwordEncoder.matches(password, user.getPassword());

    if (!isValidPassword) {
      throw new Unauthorized("Nome ou senha não existem.");
    }

    String token = JwtUtil.generateToken(user.getName());

    return token;
  }
}
