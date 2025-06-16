package com.wollyver.java_api.controllers;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wollyver.java_api.dto.ApiResponse;
import com.wollyver.java_api.dto.auth.LoginRequestDTO;
import com.wollyver.java_api.dto.auth.LoginResponseDTO;
import com.wollyver.java_api.models.User;
import com.wollyver.java_api.security.JwtUtil;
import com.wollyver.java_api.services.AuthService;
import com.wollyver.java_api.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private final UserService userService;
  private final AuthService authService;
  private final PasswordEncoder passwordEncoder;

  public AuthController(UserService userService, PasswordEncoder passwordEncoder, AuthService authService) {
    this.userService = userService;
    this.passwordEncoder = passwordEncoder;
    this.authService = authService;
  } 
  
  @PostMapping("/login")
  public ResponseEntity<ApiResponse<LoginResponseDTO>> login(@RequestBody LoginRequestDTO body) {
    String token = authService.auth(body.getName(), body.getPassword());
    
    ApiResponse<LoginResponseDTO> response = new ApiResponse<>(true, "Login realizado com sucesso", 200, new LoginResponseDTO(token));

    return ResponseEntity.status(200).body(response);
  }
}
