package com.wollyver.java_api.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wollyver.java_api.models.User;
import com.wollyver.java_api.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;
  public UserController(UserService userService) {
    this.userService = userService;
  }
  
  @GetMapping
  public List<User> getUsers() {
    return userService.getAllUsers();
  }

  @PostMapping("/create")
  public User createUser(@RequestBody User user) {
    return userService.createUser(user);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteUser(@PathVariable Long id) {
    String message = userService.deleteUserById(id);
    return ResponseEntity.ok(message);
  }

  @PutMapping("/update/{id}")
  public User updateUser(@PathVariable Long id, @RequestBody User newUser) {
    User userUpdated = userService.updateUser(id, newUser);
    return userUpdated;
  }
  
}
