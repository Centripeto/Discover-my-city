package com.boomers.www.discover_my_city.core.model.municipality;

import com.boomers.www.discover_my_city.core.model.user.User;

public class UserMunicipality {
  User user;
  Municipality municipality;

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Municipality getMunicipality() {
    return municipality;
  }

  public void setMunicipality(Municipality municipality) {
    this.municipality = municipality;
  }
}
