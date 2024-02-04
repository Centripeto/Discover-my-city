package com.boomers.www.discover_my_city.core.model.auth;

import com.boomers.www.discover_my_city.core.model.user.User;

public class Token {
  public String token;

  public boolean revoked;

  public boolean expired;

  public User user;

  public Token() {}

  public Token(String token, boolean revoked, boolean expired, User user) {
    this.token = token;
    this.revoked = revoked;
    this.expired = expired;
    this.user = user;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public boolean isRevoked() {
    return revoked;
  }

  public void setRevoked(boolean revoked) {
    this.revoked = revoked;
  }

  public boolean isExpired() {
    return expired;
  }

  public void setExpired(boolean expired) {
    this.expired = expired;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
