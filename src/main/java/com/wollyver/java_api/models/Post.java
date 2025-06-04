package com.wollyver.java_api.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Post {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private String content;
  private int likesAmount;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  public Post() {}

  public Post(String title, String content, int likesAmount) {
    this.title = title;
    this.content = content;
    this.likesAmount = likesAmount;
  }

  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public int getLikesAmount() {
    return likesAmount;
  }

  public void setLikesAmount(int likesAmount) {
    this.likesAmount = likesAmount;
  }

  public User getUser() {
    return user;
  }
  
  public void setUser(User user) {
    this.user = user;
  }


}
