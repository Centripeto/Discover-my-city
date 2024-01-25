package com.boomers.www.discover_my_city.api.dto;

public class AuthenticationRequestDto {

  private String username;
  String password;

  public String getUsername() {
    return username;
  }

  public AuthenticationRequestDto() {}

  public AuthenticationRequestDto(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
