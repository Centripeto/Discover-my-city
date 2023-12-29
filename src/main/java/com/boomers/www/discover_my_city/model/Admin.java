package com.boomers.www.discover_my_city.model;

public class Admin extends User {
  public Admin() {
    super();
  }

  public Admin(String name, String surname, String email) {
    super(name, surname, email, Role.ADMIN);
  }

  public Admin(String id, String name, String surname, String email) {
    super(id, name, surname, email, Role.ADMIN);
  }
}
