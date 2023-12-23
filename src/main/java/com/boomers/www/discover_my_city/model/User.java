package com.boomers.www.discover_my_city.model;

public abstract class User {
  private String id;
  private String name;
  private String surname;
  private String email;
  private Role role;

  public User() {}

  public User(String name, String surname, String email, Role role) {
    this.name = name;
    this.surname = surname;
    this.email = email;
    this.role = role;
  }

  public User(String id, String name, String surname, String email, Role role) {
    this.id = id;
    this.name = name;
    this.surname = surname;
    this.email = email;
    this.role = role;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  @Override
  public String toString() {
    String json = """
        {
            "id": "%s",
            "name": "%s",
            "surname": "%s",
            "email": "%s",
            "role": %s,
        }
      """;
    return String.format(json, this.id, this.name, this.surname, this.email, this.role);
  }
}
