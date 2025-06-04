package com.wollyver.java_api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wollyver.java_api.models.User;
import com.wollyver.java_api.repositories.UserRepository;

@Service
public class UserService {
  
  
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public User getUserById(Long id) {
    return userRepository.getReferenceById(id);
  }

  public User createUser(User user) {
    return userRepository.save(user);
  }

  public String deleteUserById(Long id) {
    userRepository.deleteById(id);
    return "Usuário deletado com sucesso";
  }

  public User updateUser(Long id, User newUser) {
    User user = this.getUserById(id);
    user.setName(newUser.getName());
    user.setPassword(newUser.getPassword());
    return userRepository.save(user);
  }
  
}
