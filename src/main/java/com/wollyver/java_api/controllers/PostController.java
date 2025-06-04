package com.wollyver.java_api.controllers;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wollyver.java_api.models.Post;
import com.wollyver.java_api.services.PostService;

@RestController
@RequestMapping("/posts")
public class PostController {
  private final PostService postService;

  public PostController(PostService postService) {
    this.postService = postService;
  }

  @PostMapping("/create/{userId}")
  public ResponseEntity<Post> createPost(@PathVariable Long userId, @RequestBody Post post) {
    Post newPost = postService.createPost(post, userId);
    return ResponseEntity.status(201).body(newPost);
  }

}
