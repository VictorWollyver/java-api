package com.wollyver.java_api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wollyver.java_api.exceptions.AlreadyExists;
import com.wollyver.java_api.exceptions.NotFound;
import com.wollyver.java_api.models.User;
import com.wollyver.java_api.repositories.UserRepository;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public User getUserById(Long id) {
    return userRepository.findById(id).orElseThrow(() -> new NotFound("Usuário não encontrado"));
  }

  public User getUserByName(String name) {
    Optional<User> user = userRepository.findByName(name);
    if (user.isEmpty())
      throw new NotFound("Usuário não encontrado");
    return user.get();
  }

  public User createUser(User user) {
    User userAlreadyExists = this.getUserByName(user.getName());
    if (userAlreadyExists != null)
      throw new AlreadyExists("Um usuário com esse nome já existe");

    user.setPassword(passwordEncoder.encode(user.getPassword()));

    return userRepository.save(user);
  }

  public String deleteUserById(Long id) {

    if (!userRepository.existsById(id))
      throw new NotFound("Não é possivel deletar um usuário que não existe");

    userRepository.deleteById(id);
    return "Usuário deletado com sucesso";
  }

  public User updateUser(Long id, User newUser) {
    if (!userRepository.existsById(id))
      throw new NotFound("Não é possivel atualizar um usuário que não existe");

    User user = this.getUserById(id);
    user.setName(newUser.getName());
    user.setPassword(newUser.getPassword());
    return userRepository.save(user);
  }

}
