package com.boomers.www.discover_my_city.api.dto;

public class UserDto {
  private String name;
  private String username;
  private String lastname;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastName) {
    this.lastname = lastName;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}
