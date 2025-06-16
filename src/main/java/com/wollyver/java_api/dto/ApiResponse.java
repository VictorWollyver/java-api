package com.wollyver.java_api.dto;

public class ApiResponse<T> {
  private boolean isSuccess;
  private String message;
  private int statusCode;
  private T data;

  public ApiResponse(boolean isSuccess, String message, int statusCode, T data) {
    this.isSuccess = isSuccess;
    this.message = message;
    this.statusCode = statusCode;
    this.data = data;
  }

  public T getData() {
    return data;
  }
  public void setData(T data) {
    this.data = data;
  }

  public String getMessage() {
    return message;
  }
  public void setMessage(String message) {
    this.message = message;
  }

  public boolean getIsSuccess() {
    return isSuccess;
  }
  public void setIsSuccess(boolean isSuccess) {
    this.isSuccess = isSuccess;
  }

  public int getStatusCode() {
    return statusCode;
  }
  public void setStatusCode(int statusCode) {
    this.statusCode = statusCode;
  }
}