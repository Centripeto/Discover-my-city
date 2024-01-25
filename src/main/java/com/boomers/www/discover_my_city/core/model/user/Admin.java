package com.boomers.www.discover_my_city.core.model.user;

public class Admin extends User {
  public Admin(String name, String username, String lastname, String email, String password) {
    super(name, username, lastname, email, password, Role.ADMIN);
  }
}
