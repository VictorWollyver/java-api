package com.wollyver.java_api.exceptions;

public class AlreadyExists extends RuntimeException {
   public AlreadyExists(String message) {
    super(message);
  }
}
