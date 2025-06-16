package com.wollyver.java_api.exceptions;

public class Unauthorized extends RuntimeException {

  public Unauthorized(String message) {
    super(message);
  }
}
