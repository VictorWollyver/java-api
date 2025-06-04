package com.wollyver.java_api.services;

import org.springframework.stereotype.Service;

import com.wollyver.java_api.exceptions.NotFound;
import com.wollyver.java_api.models.Post;
import com.wollyver.java_api.models.User;
import com.wollyver.java_api.repositories.PostRepository;

@Service
public class PostService {
  private final PostRepository postRepository;
  private final UserService userService;

  public PostService(PostRepository postRepository, UserService userService) {
    this.postRepository = postRepository;
    this.userService = userService;
  }

  public Post createPost(Post post, Long userId) {
    User user = userService.getUserById(userId);
    if(user == null) throw new NotFound("Não é possivel criar um post. Usuário não existe");
    post.setUser(user);

    return postRepository.save(post);
  }
}
