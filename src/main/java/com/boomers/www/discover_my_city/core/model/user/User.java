package com.boomers.www.discover_my_city.core.model.user;

import com.boomers.www.discover_my_city.core.model.municipality.Municipality;

public class User {

  private Integer id;

  private String name;

  private String username;

  private String lastname;

  private String email;

  private String password;

  private Role role;

  private Municipality municipality;

  public User() {}

  public User(
      Integer id,
      String name,
      String username,
      String lastname,
      String email,
      String password,
      Role role) {
    this.id = id;
    this.name = name;
    this.username = username;
    this.lastname = lastname;
    this.email = email;
    this.password = password;
    this.role = role;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Role getRole() {
    return role;
  }

  public Municipality getMunicipality() {
    return municipality;
  }

  public void setMunicipality(Municipality municipality) {
    this.municipality = municipality;
  }
}
