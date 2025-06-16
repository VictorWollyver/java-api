package com.wollyver.java_api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wollyver.java_api.models.User;
import com.wollyver.java_api.security.JwtUtil;
import com.wollyver.java_api.services.AuthService;
import com.wollyver.java_api.services.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public List<User> getAllUsers() {
    return userService.getAllUsers();
  }

  @PostMapping("/create")
  public ResponseEntity<?> createUser(@RequestBody User user) {
    User newUser = userService.createUser(user);
    return ResponseEntity.status(201).body(newUser);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteUser(@PathVariable Long id) {
    String message = userService.deleteUserById(id);
    return ResponseEntity.ok(message);
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User newUser) {
    User userUpdated = userService.updateUser(id, newUser);
    return ResponseEntity.status(200).body(userUpdated);
  }

  @GetMapping("/private")
  public ResponseEntity<?> getUserByToken(HttpServletRequest request) {
    String username = request.getAttribute("username").toString();
    User user = userService.getUserByName(username);

    return ResponseEntity.status(200).body(user);
  }
}
