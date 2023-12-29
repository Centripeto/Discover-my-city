package com.boomers.www.discover_my_city.model;

public class Contributor extends User {

  public Contributor() {
    super();
  }

  public Contributor(String name, String surname, String email) {
    super(name, surname, email, Role.CONTRIBUTOR);
  }

  public Contributor(String id, String name, String surname, String email) {
    super(id, name, surname, email, Role.CONTRIBUTOR);
  }
}
